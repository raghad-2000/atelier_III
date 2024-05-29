package com.card.service.card.controllers;

import com.card.service.card.dto.CardDTO;
import com.card.service.card.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cards-api/v1")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping(value = "/cards")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<List<CardDTO>> getCards() {
        return ResponseEntity.ok(cardService.findAll());
    }


}
