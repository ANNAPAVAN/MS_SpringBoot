package com.pavan.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {

	@Size(min = 4, message = "Name must be at least 4 characters long")
	private String name;
	private String description;
	private BigDecimal price;
	
}
