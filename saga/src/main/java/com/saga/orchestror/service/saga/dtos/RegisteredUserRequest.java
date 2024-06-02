package com.saga.orchestror.service.saga.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisteredUserRequest {
    private String username;
    private String password;
}