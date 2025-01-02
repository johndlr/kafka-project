package com.juandlr.emailnotification.handler;

import com.juandlr.emailnotification.entity.ProcessedEventEntity;
import com.juandlr.emailnotification.exception.NoRetryableException;
import com.juandlr.emailnotification.exception.RetryableException;
import com.juandlr.emailnotification.repository.ProcessedEventRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.juandlr.core.ProductCreatedEvent;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@KafkaListener(topics="product-created-events-topic")
public class ProductCreatedEventHandler {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private final RestTemplate restTemplate;

	private final ProcessedEventRepository processedEventRepository;

	public ProductCreatedEventHandler(RestTemplate restTemplate, ProcessedEventRepository processedEventRepository) {
		this.restTemplate = restTemplate;
		this.processedEventRepository = processedEventRepository;
	}

	@Transactional
	@KafkaHandler
	public void handle(@Payload ProductCreatedEvent productCreatedEvent) {
		LOGGER.info("Received a new event: " + productCreatedEvent.getTitle() + " Id Product: " + productCreatedEvent.getProductId());
		String requestUrl = "http://localhost:8082/response/200";

		Optional<ProcessedEventEntity> optionalProcessedEvent = processedEventRepository.findByProductId(productCreatedEvent.getProductId());
		if (optionalProcessedEvent.isPresent()){
			LOGGER.info("Found a duplicate product id: " + optionalProcessedEvent.get().getProductId());
			return;
		}

		try{
			ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.GET, null, String.class);
			if (response.getStatusCode().value() == HttpStatus.OK.value()){
				LOGGER.info("Received response from a remote service: " + response.getBody());
			}
		}catch (ResourceAccessException ex){
			LOGGER.error(ex.getMessage());
			throw new RetryableException(ex);
		} catch (Exception ex){
			LOGGER.error(ex.getMessage());
			throw new NoRetryableException(ex);
		}

		try{
			processedEventRepository.save(new ProcessedEventEntity(productCreatedEvent.getProductId()));
			LOGGER.info("Processed Event OK");
		}catch (DataIntegrityViolationException ex){
			throw new NoRetryableException(ex);
		}
	}
	
}
