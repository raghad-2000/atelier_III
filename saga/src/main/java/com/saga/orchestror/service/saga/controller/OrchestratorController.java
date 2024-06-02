package com.saga.orchestror.service.saga.controller;


import com.saga.orchestror.service.saga.dtos.UserCardRequest;
import com.saga.orchestror.service.saga.service.OrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orchestrator")
public class OrchestratorController {

    @Autowired
    private OrchestratorService orchestratorService;

    @PostMapping("/createUserAndBuyCard")
    public void createUserAndBuyCard(@RequestBody UserCardRequest request) {
        orchestratorService.createUserAndBuyCard(request.getUser(), request.getCardId());
    }

    @PostMapping("/sellCard")
    public void sellCard(@RequestBody UserCardRequest request) {
        orchestratorService.sellCard(request.getUserId(), request.getCardId());
    }

}
