package com.saga.orchestror.service.saga.service;

import com.saga.orchestror.service.saga.client.AuthenticationClient;
import com.saga.orchestror.service.saga.client.UserClient;
import com.saga.orchestror.service.saga.client.CardClient;
import com.saga.orchestror.service.saga.client.TransactionClient;
import com.saga.orchestror.service.saga.dtos.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrchestratorService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private CardClient cardClient;

    @Autowired
    private AuthenticationClient authenticationClient;

    @Autowired
    private TransactionClient transactionClient;

    @Transactional
    public void orchestrateSignup(RegisteredUserRequest registeredUser) {
        try {
            // Créez l'utilisateur
            authenticationClient.addUser(registeredUser);
            userClient.createUser(new AppUserDto(registeredUser.getUsername(), 4000));
            AppUserDto appUser = userClient.retrieveUserInfo(registeredUser.getUsername());
            List<Long> integerList = cardClient.getRandomCards(5).getBody();
            if (integerList != null)
            {
                for (Long i : integerList)
                {
                    TransactionRequest transactionRequest = new TransactionRequest(appUser.getId(), i, "buy");
                    transactionClient.transaction(transactionRequest);
                }
            }
        } catch (Exception e) {

            throw e;
        }
    }


    /**
     * entree : userid, cardid
     * 1* récupérer le prix de la carte
     * 2* creer l'association de transaction
     * 3* enlever le prix de l'utilisateur
     * @param orchestratorUserCardRequest
     */
    @Transactional
    public void buyCard(OrchestratorUserCardRequest orchestratorUserCardRequest) {

        // récupérer le prix de la carte
        CardDto card = cardClient.getCardById(orchestratorUserCardRequest.getCardId());
        AppUserDto appUser = userClient.retrieveUserInfo(orchestratorUserCardRequest.getUserName());

        // todo: handle error

        // creer l'association de transaction

        TransactionRequest transactionRequest = new TransactionRequest(appUser.getId(), card.getId(), "buy");

        //enlever le prix de l'utilisateur
        double newMoney = appUser.getMoney() - card.getPrice();
        try {
            transactionClient.transaction(transactionRequest);
            appUser.setMoney(newMoney);
            userClient.createUser(appUser);
        } catch (Exception e) {
            // Effectuez le rollback si nécessaire
            transactionRequest.setType("sell");
            transactionClient.transaction(transactionRequest);
            if (appUser != null) {
                appUser.setMoney(appUser.getMoney());
            }
            throw e;
        }
    }


    @Transactional
    public void sellCard(OrchestratorUserCardRequest orchestratorUserCardRequest) {
        // récupérer le prix de la carte

        CardDto card = cardClient.getCardById(orchestratorUserCardRequest.getCardId());
        AppUserDto appUser = userClient.retrieveUserInfo(orchestratorUserCardRequest.getUserName());

        // creer l'association de transaction
        TransactionRequest transactionRequest = new TransactionRequest(appUser.getId(), card.getId(), "sell");

        //calculer new userMoney + get user to update
        double newMoney = appUser.getMoney() + card.getPrice();


        try {
            // Créez l'utilisateur
            transactionClient.transaction(transactionRequest);
            appUser.setMoney(newMoney);
            userClient.createUser(appUser);
        } catch (Exception e) {
            // Effectuez le rollback si nécessaire
            transactionRequest.setType("buy");
            transactionClient.transaction(transactionRequest);
            if (appUser != null) {
                appUser.setMoney(appUser.getMoney());
            }
            throw e;
        }
    }
    @Transactional
    public AppUserDtoWithCards getUserWithCards(AppUserDto appUser) {
        // 1 find ALL transaction by user ID
        // 2 For each transaction, find card with cardClient
        // 3 return new AppUserDtoWithCards

        List<TransactionDto> transactionList = transactionClient.getTransactionById(appUser.getId());
        List <CardDto> cards = new ArrayList<>();
        for(TransactionDto transaction: transactionList ) {
            cards.add(cardClient.getCardById(transaction.getCardId()));
            cards.get(cards.size()-1).setQuantity(transaction.getQuantity());
        }
        return new AppUserDtoWithCards(appUser.getUsername(), appUser.getMoney(), cards);
    }

}
