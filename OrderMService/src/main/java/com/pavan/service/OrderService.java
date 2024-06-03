package com.pavan.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.pavan.dto.InventoryResponse;
import com.pavan.dto.OrderLineItemsDto;
import com.pavan.dto.OrderRequest;
import com.pavan.model.Order;
import com.pavan.model.OrderLineItems;
import com.pavan.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
//@Transactional(readOnly=true)
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final WebClient webClient;
	
	public String placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
			.stream()
			.map(this::mapToDto)
			.toList();
		
		order.setOrderLineItemsList(orderLineItems);
		
		List<String> skuCodes =  order.getOrderLineItemsList().stream()
				.map(orderListItem -> orderListItem.getSkuCode())
				.toList();
		
		
		// Call to Inventory Service and place order if product is in stock
		InventoryResponse[] inventoryResponses = webClient.get()
			.uri("http://inventory-service/api/inventory",
					uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
				.retrieve()
					.bodyToMono(InventoryResponse[].class)
						.block();

        boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(inventoryResponse -> inventoryResponse.isInStock());
		
		if(allProductsInStock) {
			orderRepository.save(order);
			return "Order placed Successfully";
		}else {
			throw new IllegalArgumentException("Product is not in stock , please try again later");
		}
						
//		orderRepository.save(order);
	}
	
	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) { 
		OrderLineItems orderLineItems =new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
		
	}
}
