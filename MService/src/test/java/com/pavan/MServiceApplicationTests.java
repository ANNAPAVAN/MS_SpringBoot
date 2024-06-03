package com.pavan;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pavan.dto.ProductRequest;

@SpringBootTest(classes = MServiceApplication.class)
@Testcontainers
@AutoConfigureMockMvc
class MServiceApplicationTests {
//	https://testcontainers.com/

    @Container
    static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.10");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // convert POJO object to json and vice versa

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        ProductRequest productRequest = getProductRequest();
        String productReqString = objectMapper.writeValueAsString(productRequest);
       

        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productReqString))
                .andExpect(status().isCreated());
        

    }
    
    
    @Test
    void shouldCreateProduct2() throws Exception {
        ProductRequest productRequest2 = getProductRequest2();
        String productReqString2 = objectMapper.writeValueAsString(productRequest2);
        
        
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productReqString2))
                .andExpect(status().isCreated());
    }

    
    
    private ProductRequest getProductRequest() {
        return ProductRequest.builder()
                .name("Redmi Note 9 Pro Max")
                .description("Redmi Note 9 Pro Max")
                .price(BigDecimal.valueOf(15000))
                .build();
    }
    
    
    private ProductRequest getProductRequest2() {
        return ProductRequest.builder()
                .name("Redmi")
                .description("Redmi")
                .price(BigDecimal.valueOf(9000))
                .build();
    }
}
