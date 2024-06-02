package com.transaction.service.transaction.controllers;

import com.transaction.service.transaction.dtos.UserCardRequest;
import com.transaction.service.transaction.orchestrator.OrchestratorServiceClient;
import com.transaction.service.transaction.services.TransactionService;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    private OrchestratorServiceClient orchestratorServiceClient;

    @PostMapping(value = "/buy")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<HttpStatus> buyCard(@RequestBody String card, @RequestHeader("user") String idUsername) {
        // User will be in header
        // String username = jwtService.extractUsername(token.substring(7));
        JsonObject cardJson = new Gson().fromJson(card, JsonObject.class);
        if (cardJson.get("id") != null) {
            //  appel orchestrator
            UserCardRequest userCardRequest = new UserCardRequest(idUsername, Long.parseLong(cardJson.get("id").getAsString()));
            orchestratorServiceClient.buyCard(userCardRequest);
            //transactionService.buyCard(idUsername, cardJson.get("id").getAsString());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }
    @PostMapping(value = "/sell")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<HttpStatus> sellCard(@RequestBody String card, @RequestHeader("user") String idUsername) {
        JsonObject cardJson = new Gson().fromJson(card, JsonObject.class);
        if (cardJson.get("id") != null) {
            transactionService.sellCard(idUsername, cardJson.get("id").getAsString());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }
}
