package com.transaction.service.transaction.controllers;

import com.transaction.service.transaction.dtos.OrchestratorUserCardRequest;
import com.transaction.service.transaction.dtos.TransactionRequest;
import com.transaction.service.transaction.orchestrator.OrchestratorServiceClient;
import com.transaction.service.transaction.services.TransactionService;
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
    public ResponseEntity<HttpStatus> buyCard(@RequestBody String card, @RequestHeader("user") String username) {
        JsonObject cardJson = new Gson().fromJson(card, JsonObject.class);
        if (cardJson.get("id") != null) {
            // todo: handle error
            orchestratorServiceClient.buyCard(new OrchestratorUserCardRequest(username, Long.parseLong(cardJson.get("id").getAsString())));
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping(value = "/sell")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<HttpStatus> sellCard(@RequestBody String card, @RequestHeader("user") String username) {
        JsonObject cardJson = new Gson().fromJson(card, JsonObject.class);
        if (cardJson.get("id") != null) {
            // todo: handle error
            orchestratorServiceClient.sellCard(new OrchestratorUserCardRequest(username, Long.parseLong(cardJson.get("id").getAsString())));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }

    @PostMapping(value = "/transaction")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<HttpStatus> transaction(@RequestBody TransactionRequest transactionRequest) {
        if (transactionRequest.getType().equalsIgnoreCase("buy")) {
            // buy
            transactionService.buyCard(transactionRequest.getUserId(), transactionRequest.getCardId());
        } else {
            // sell
            transactionService.sellCard(transactionRequest.getUserId(), transactionRequest.getCardId());
        }
        return ResponseEntity.ok().build();
    }


}
