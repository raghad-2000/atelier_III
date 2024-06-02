package com.saga.orchestror.service.saga.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionRequest {

    private String type;
    private Long cardId;
    private Long userId;

    public TransactionRequest(Long userId, Long cardId, String type) {
        this.userId = userId;
        this.cardId = cardId;
        this.type = type;
    }

}
