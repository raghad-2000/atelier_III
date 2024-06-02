package com.saga.orchestror.service.saga.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AppUserDto {
    private Long id;
    private String username;
    private double money;

    public AppUserDto(String username, double money)
    {
        this.username = username;
        this.money = money;
    }
}
