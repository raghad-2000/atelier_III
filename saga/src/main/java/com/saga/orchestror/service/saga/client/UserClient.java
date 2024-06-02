package com.saga.orchestror.service.saga.client;
import com.saga.orchestror.service.saga.dtos.AppUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user")

public interface UserClient {
    @GetMapping("/users/{id}")
    AppUserDto getUserById(@PathVariable("id") Long id);


    @PostMapping("/users")
    AppUserDto createUser(@RequestBody AppUserDto user);

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable("id") Long id);
}
