package com.saga.orchestror.service.saga.service;

import com.saga.orchestror.service.saga.client.UserClient;
import com.saga.orchestror.service.saga.client.CardClient;
import com.saga.orchestror.service.saga.client.TransactionClient;
import com.saga.orchestror.service.saga.dtos.AppUserDto;
import com.saga.orchestror.service.saga.dtos.CardDto;
import com.saga.orchestror.service.saga.dtos.TransactionDto;
import com.saga.orchestror.service.saga.dtos.TransactionRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrchestratorService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private CardClient cardClient;

    @Autowired
    private TransactionClient transactionClient;

    @Transactional
    public void createUserAndBuyCard(AppUserDto user, Long cardId) {
        AppUserDto createdUser = null;
        TransactionDto createdTransaction = null;

        try {
            // Créez l'utilisateur
            createdUser = userClient.createUser(user);

            // Récupérez la carte par ID
            CardDto card = cardClient.getCardById(cardId);
            card.setUserId(createdUser.getId());
            cardClient.updateCard(card);

            // Achetez la carte
            TransactionRequest request = new TransactionRequest();
            request.setUserId(createdUser.getId());
            request.setCardId(cardId);
            createdTransaction = transactionClient.buyCard(request);
        } catch (Exception e) {
            // Effectuez le rollback si nécessaire
            if (createdTransaction != null) {
                transactionClient.rollbackBuy(createdTransaction.getId());
            }
            if (createdUser != null) {
                userClient.deleteUser(createdUser.getId());
            }
            throw e;
        }
    }


    @Transactional
    public void sellCard(Long userId, Long cardId) {
        TransactionDto createdTransaction = null;

        try {
            // Vendez la carte
            TransactionRequest request = new TransactionRequest();
            request.setUserId(userId);
            request.setCardId(cardId);
            createdTransaction = transactionClient.sellCard(request);

            // Mettez à jour la carte pour enlever l'utilisateur
            CardDto card = cardClient.getCardById(cardId);
            card.setUserId(null);
            cardClient.updateCard(card);
        } catch (Exception e) {
            // Effectuez le rollback si nécessaire
            if (createdTransaction != null) {
                transactionClient.rollbackSell(createdTransaction.getId());
            }
            throw e;
        }
    }

}