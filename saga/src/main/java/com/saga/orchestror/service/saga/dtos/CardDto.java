package com.saga.orchestror.service.saga.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CardDto {
    private Long id;
    private String name;
    private String description;
    private String family;
    private String affinity;
    private float energy;
    private float hp;
    private float price;
    private float rarity;
    private String url;
    private Long userId; // Null if the card is not assigned to any user

}
