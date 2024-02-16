package com.hobytech.inventoryservice.controller;

import com.hobytech.inventoryservice.dto.InventoryResponse;
import com.hobytech.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    @Autowired
    private final InventoryService inventoryService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) throws InterruptedException {
        log.info("Wait Started"); //implemented to test timeout & retries
       // Thread.sleep(10000); //implemented to test timeout & retries
        log.info(("Wait Ended")); //implemented to test timeout & retries
        return inventoryService.isInStock(skuCode);
    }
}
