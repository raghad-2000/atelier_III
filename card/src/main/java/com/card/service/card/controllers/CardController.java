package com.card.service.card.controllers;

import com.card.service.card.dto.CardDTO;
import com.card.service.card.entities.Card;
import com.card.service.card.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping(value = "/cards")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<List<CardDTO>> getCards() {
        return ResponseEntity.ok(cardService.findAll());
    }

    @GetMapping(value = "/random-cards")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<List<Long>> getRandomCards(@RequestParam Integer qty) {
        return ResponseEntity.ok(cardService.getRandomCards(qty));
    }

    @GetMapping(value = "/card/{id}")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<Card> getCard(@PathVariable("id") Long id) {
        return ResponseEntity.ok(cardService.getCard(id).get());
    }



}
