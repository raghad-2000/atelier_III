package com.saga.orchestror.service.saga.service;

import com.saga.orchestror.service.saga.client.UserClient;
import com.saga.orchestror.service.saga.client.CardClient;
import com.saga.orchestror.service.saga.client.TransactionClient;
import com.saga.orchestror.service.saga.dtos.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrchestratorService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private CardClient cardClient;

    @Autowired
    private TransactionClient transactionClient;

    @Transactional
    public void orchestrateSignup(RegisteredUserRequest registeredUser) {

        // créer l'utilisateur authentfication username / mdp

        // créer l'utilisateur joueur appuser username / money
        AppUserDto newUser = new AppUserDto(registeredUser.getUsername(), 1);

        // attribuer 5 cartes à l'utilisateur


        try {
            // Créez l'utilisateur
            userClient.createUser(newUser);
        } catch (Exception e) {
            // Effectuez le rollback si nécessaire
            if (newUser != null) {
                userClient.deleteUser(newUser.getId());
            }
            throw e;
        }
    }


    /**
     * entree : userid, cardid
     * 1* récupérer le prix de la carte
     * 2* creer l'association de transaction
     * 3* enlever le prix de l'utilisateur
     * @param userCard
     */
    @Transactional
    public void buyCard(UserCardRequest userCard) {

        // récupérer le prix de la carte
        float cardPrice = cardClient.getCardById(userCard.getCardId()).getPrice();
        double userMoney = userClient.getUserById(userCard.getUserId()).getMoney();

        // creer l'association de transaction
        TransactionRequest transactionRequest = new TransactionRequest(userCard.getUserId(), userCard.getCardId());

        //enlever le prix de l'utilisateur
        double newMoney = userMoney - cardPrice;
        AppUserDto appUser = userClient.getUserById(userCard.getUserId());

        try {
            // Créez l'utilisateur
            transactionClient.buyCard(transactionRequest);
            appUser.setMoney(newMoney);
            userClient.createUser(appUser);
        } catch (Exception e) {
            // Effectuez le rollback si nécessaire
            transactionClient.sellCard(transactionRequest);
            if (appUser != null) {
                appUser.setMoney(userMoney);
            }
            throw e;
        }
    }


    @Transactional
    public void sellCard(UserCardRequest userCard) {
        // récupérer le prix de la carte
        float cardPrice = cardClient.getCardById(userCard.getCardId()).getPrice();
        double userMoney = userClient.getUserById(userCard.getUserId()).getMoney();

        // creer l'association de transaction
        TransactionRequest transactionRequest = new TransactionRequest(userCard.getUserId(), userCard.getCardId());

        //calculer new userMoney + get user to update
        double newMoney = userMoney + cardPrice;
        AppUserDto appUser = userClient.getUserById(userCard.getUserId());

        try {
            // Créez l'utilisateur
            transactionClient.sellCard(transactionRequest);
            appUser.setMoney(newMoney);
            userClient.createUser(appUser);
        } catch (Exception e) {
            // Effectuez le rollback si nécessaire
            transactionClient.buyCard(transactionRequest);
            if (appUser != null) {
                appUser.setMoney(userMoney);
            }
            throw e;
        }
    }

}
