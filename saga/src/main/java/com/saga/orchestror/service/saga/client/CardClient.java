package com.saga.orchestror.service.saga.client;


import com.saga.orchestror.service.saga.dtos.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "card")
public interface CardClient {
    @GetMapping("/cards/{id}")
    CardDto getCardById(@PathVariable("id") Long id);

    @PutMapping("/cards/{id}")
    void updateCard(@RequestBody CardDto card);
}
