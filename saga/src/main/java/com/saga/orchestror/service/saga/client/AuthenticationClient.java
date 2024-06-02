package com.saga.orchestror.service.saga.client;

import com.saga.orchestror.service.saga.dtos.RegisteredUserRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("authentication")
public interface AuthenticationClient
{
    @PostMapping("/adduser")
    ResponseEntity<RegisteredUserRequest> addUser(@RequestBody RegisteredUserRequest registeredUserRequest);
}
