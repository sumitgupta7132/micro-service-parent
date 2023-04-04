package com.microservices.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.productservice.common.dto.ProductRequest;
import com.microservices.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
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

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
	private static final String  dockerImageName = "mongo:4.4.2";

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer(dockerImageName);

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductRepository productRepository;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
	}
	@Test
	void createProduct_validFields_ProductCreated() throws Exception {
		//Given
		ProductRequest productRequest = getSampleProductRequest();
		String productRequestString = objectMapper.writeValueAsString(productRequest);
		//When
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString))
				.andExpect(status().isCreated());
		// Then
		Assertions.assertEquals(1,productRepository.findAll().size());
	}

	private ProductRequest getSampleProductRequest() {
		return ProductRequest.builder().name("IPad").description("Tab").price(BigDecimal.valueOf(1400)).build();
	}

}
