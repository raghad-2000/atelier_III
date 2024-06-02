package com.transaction.service.transaction.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserCardRequest {
    private String userName;
    private Long cardId;

    public UserCardRequest(String userName, Long cardId) {
        this.userName = userName;
        this.cardId = cardId;
    }
}
