package com.transaction.service.transaction.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TransactionRequest {

    private String type;
    private Long cardId;
    private Long userId;

}
