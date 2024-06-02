package com.card.service.card.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Card {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    private long id;
    private long palId;
    private String name;
    private String description;
    private String family;
    private String affinity;
    private float energy;
    private float hp;
    private float price;
    private float rarity;
    private String url;

    public Card() {
        this.id = 0;
        this.name = "";
        this.description = "";
        this.family = "";
        this.affinity = "";
        this.energy = 0;
        this.hp = 0;
        this.price = 0;
    }
    public Card(long id, String name, String description, String affinity, String family, float energy, float hp, float price, float rarity, String url) {
        this.palId = id;
        this.name = name;
        this.description = description;
        this.affinity = affinity;
        this.family = family;
        this.energy = energy;
        this.hp = hp;
        this.price = price;
        this.rarity = rarity;
        this.url = url;
    }

}
