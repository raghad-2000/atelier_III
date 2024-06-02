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
    private String type;
    private Date date;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCardId() {
        return cardId;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
