package com.user.service.appuser.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class AppUserDtoWithCards {

    private double money;
    private String username;
    private List<CardDto> cards;


}
