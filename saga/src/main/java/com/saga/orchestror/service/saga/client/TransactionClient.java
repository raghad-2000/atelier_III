package com.saga.orchestror.service.saga.client;

import com.saga.orchestror.service.saga.dtos.CardDto;
import com.saga.orchestror.service.saga.dtos.TransactionDto;
import com.saga.orchestror.service.saga.dtos.TransactionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "transaction")
public interface TransactionClient {
    @PostMapping("/transaction")
    HttpStatus transaction(@RequestBody TransactionRequest request);

    @GetMapping("/findTransactionById/{id}")
    List<TransactionDto> getTransactionById(@PathVariable("id") Long id);

}
