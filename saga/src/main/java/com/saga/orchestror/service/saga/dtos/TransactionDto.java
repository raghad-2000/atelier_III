package com.saga.orchestror.service.saga.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class TransactionDto {
    private Long id;
    private Long userId;
    private Long cardId;
    private int quantity;
}
