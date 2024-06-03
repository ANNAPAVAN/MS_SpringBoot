package com.pavan.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pavan.dto.InventoryResponse;
import com.pavan.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.SneakyThrows;


@Service
@RequiredArgsConstructor 
@Slf4j
public class InventoryService {
	
	private final InventoryRepository inventoryRepository;
	
	@Transactional(readOnly=true)
	@SneakyThrows
	public List<InventoryResponse> isInStock(List<String> skuCode) {
		log.info("Wait started");
//		Thread.sleep(10000);    
		log.info("Wait Ended");
		return inventoryRepository.findBySkuCodeIn(skuCode).stream()
				.map(inventory -> 
					InventoryResponse.builder()
						.skuCode(inventory.getSkuCode())
						.isInStock(inventory.getQuantity() > 0)
						.build()
				).toList();
	}
}
