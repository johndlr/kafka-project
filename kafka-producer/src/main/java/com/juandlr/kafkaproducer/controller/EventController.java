package com.juandlr.kafkaproducer.controller;

import com.juandlr.kafkaproducer.dto.CustomerDto;
import com.juandlr.kafkaproducer.service.KafkaMessagePublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    private final KafkaMessagePublisher publisher;

    public EventController(KafkaMessagePublisher publisher) {
        this.publisher = publisher;
    }

//    @GetMapping("/publish/{message}")
//    public ResponseEntity<?> publishMessage(@PathVariable String message) {
//        try {
//            for (int i = 0; i <= 10000; i++) {
//                publisher.sendMessageToTopic(message + " : " + i);
//            }
//            return ResponseEntity.ok("message published successfully ..");
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .build();
//        }
//    }

    @PostMapping("/publish")
    public void sendEvents(@RequestBody CustomerDto customerDto) {
        publisher.sendEventsToTopic(customerDto);
    }
}
