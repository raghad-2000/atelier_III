package com.user.service.appuser.dto;

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
    private int quantity;
}
