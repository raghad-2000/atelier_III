package com.saga.orchestror.service.saga.client;

import com.saga.orchestror.service.saga.dtos.TransactionDto;
import com.saga.orchestror.service.saga.dtos.TransactionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "transaction")
public interface TransactionClient {
    @PostMapping("/transactions/buy")
    TransactionDto buyCard(@RequestBody TransactionRequest request);

    @PostMapping("/transactions/sell")
    TransactionDto sellCard(@RequestBody TransactionRequest request);

    @DeleteMapping("/transactions/buy/{id}")
    void rollbackBuy(@PathVariable("id") Long id);

    @DeleteMapping("/transactions/sell/{id}")
    void rollbackSell(@PathVariable("id") Long id);

}
