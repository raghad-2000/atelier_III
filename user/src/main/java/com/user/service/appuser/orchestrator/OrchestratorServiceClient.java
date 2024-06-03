package com.user.service.appuser.orchestrator;


import com.user.service.appuser.dto.AppUserDto;
import com.user.service.appuser.dto.AppUserDtoWithCards;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "saga")
public interface OrchestratorServiceClient {
    @PostMapping("/orchestrator/getUserWithCards")
    AppUserDtoWithCards getUserWithCards(@RequestBody AppUserDto orchestratorUserCardRequest);

}