package com.saga.orchestror.service.saga.controller;


import com.saga.orchestror.service.saga.dtos.RegisteredUserRequest;
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

    @PostMapping("/orchestrateSignup")
    public void orchestrateSignup(@RequestBody RegisteredUserRequest registeredUser) {
        // Call card service to create initial cards
        orchestratorService.orchestrateSignup(registeredUser);

        // Add more service calls if needed
    }

    @PostMapping("/buyCard")
    public void createUserAndBuyCard(@RequestBody UserCardRequest request) {
        orchestratorService.buyCard(request);
    }

    @PostMapping("/sellCard")
    public void sellCard(@RequestBody UserCardRequest request) {
        orchestratorService.sellCard(request);
    }

}
