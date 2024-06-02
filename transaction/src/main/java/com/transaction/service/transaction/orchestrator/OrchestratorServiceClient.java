package com.transaction.service.transaction.orchestrator;

import com.transaction.service.transaction.dtos.UserCardRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "saga")
public interface OrchestratorServiceClient {
    @PostMapping("/orchestrator/buyCard")
    void buyCard(@RequestBody UserCardRequest registerUserDto);
}