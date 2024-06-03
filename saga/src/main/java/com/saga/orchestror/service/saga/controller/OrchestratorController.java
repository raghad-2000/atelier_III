package com.saga.orchestror.service.saga.controller;


import com.saga.orchestror.service.saga.dtos.*;
import com.saga.orchestror.service.saga.service.OrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public void buyCard(@RequestBody OrchestratorUserCardRequest orchestratorUserCardRequest) {
        orchestratorService.buyCard(orchestratorUserCardRequest);
    }

    @PostMapping("/sellCard")
    public void sellCard(@RequestBody OrchestratorUserCardRequest orchestratorUserCardRequest) {
        orchestratorService.sellCard(orchestratorUserCardRequest);
    }

    @PostMapping("/getUserWithCards")
    public ResponseEntity<AppUserDtoWithCards> getUserWithCards(@RequestBody AppUserDto appUser) {
        AppUserDtoWithCards appUserDtoWithCards = orchestratorService.getUserWithCards(appUser);
        return ResponseEntity.ok(appUserDtoWithCards);
    }

}
