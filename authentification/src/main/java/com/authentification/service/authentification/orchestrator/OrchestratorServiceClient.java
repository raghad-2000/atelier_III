package com.authentification.service.authentification.orchestrator;

import com.authentification.service.authentification.dtos.RegisterUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "saga")
public interface OrchestratorServiceClient {
    @PostMapping("/orchestrator/orchestrateSignup")
    void orchestrateSignup(@RequestBody RegisterUserDto registerUserDto);
}