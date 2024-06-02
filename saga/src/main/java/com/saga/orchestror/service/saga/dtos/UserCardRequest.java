package com.saga.orchestror.service.saga.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserCardRequest {
    private AppUserDto user;
    private Long userId;
    private Long cardId;
}
