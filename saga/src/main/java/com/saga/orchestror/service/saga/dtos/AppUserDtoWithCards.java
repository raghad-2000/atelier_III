package com.saga.orchestror.service.saga.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class AppUserDtoWithCards{


    private double money;
    private String username;
    private List<CardDto> cards;
    public AppUserDtoWithCards(String username, double money, List<CardDto> cards) {
        this.username = username;
        this.money = money;
        this.cards = cards;
    }
}
