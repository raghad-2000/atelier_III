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
    @PostMapping("/transaction")
    TransactionDto transaction(@RequestBody TransactionRequest request);



}
