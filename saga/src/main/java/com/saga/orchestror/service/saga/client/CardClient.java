package com.saga.orchestror.service.saga.client;


import com.saga.orchestror.service.saga.dtos.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "card")
public interface CardClient {
    @GetMapping("/card/{id}")
    CardDto getCardById(@PathVariable("id") Long id);

    @PutMapping("/cards/{id}")
    void updateCard(@RequestBody CardDto card);

    @GetMapping("/random-cards")
    ResponseEntity<List<Integer>> getRandomCards(@RequestParam Integer qty);


}
