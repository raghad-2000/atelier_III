package com.card.service.card.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDTO {
    private long id;
    private String name;
    private String description;
    private String family;
    private String affinity;
    private float energy;
    private float hp;
    private float price;
    private float rarity;
    private String url;

    private long palId;
    public CardDTO(long id, String name, String description, String affinity, String family, float energy, float hp, float price, float rarity, String url, long palId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.affinity = affinity;
        this.family = family;
        this.energy = energy;
        this.hp = hp;
        this.price = price;
        this.rarity = rarity;
        this.url = url;
        this.palId = palId;
    }
}
