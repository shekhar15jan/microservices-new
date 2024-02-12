package com.hobytech.inventoryservice.service;

import com.hobytech.inventoryservice.dto.InventoryResponse;
import com.hobytech.inventoryservice.model.Inventory;
import com.hobytech.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{
    @Autowired
    private final InventoryRepository inventoryRepository;
//    @Override
//    @Transactional(readOnly = true)
//    public boolean isInStock(String skuCode) {
//        return inventoryRepository.findBySkuCode(skuCode).isPresent();
//    }

    @Override
    public List<InventoryResponse> isInStock(List<String> skuCodes) {
         return inventoryRepository.findBySkuCodeIn(skuCodes).stream().map(inventory ->
             InventoryResponse.builder()
                     .skuCode(inventory.getSkuCode())
                     .isInStock(inventory.getQuantity()>0)
                     .build()
         ).toList();
    }
}
