package com.transaction.service.transaction.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrchestratorUserCardRequest {
    private String userName;
    private Long cardId;

    public OrchestratorUserCardRequest(String userName, Long cardId) {
        this.userName = userName;
        this.cardId = cardId;
    }
}
