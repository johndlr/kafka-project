package com.juandlr.kafkaproducer.service;

import com.juandlr.kafkaproducer.dto.CustomerDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {
    private final KafkaTemplate<String, Object> template;

    public KafkaMessagePublisher(KafkaTemplate<String, Object> template) {
        this.template = template;
    }

//    public void sendMessageToTopic(String message){
//        CompletableFuture<SendResult<String, Object>> future = template.send("demo-topic", message);
//        future.whenComplete((result,ex)->{
//            if (ex == null) {
//                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
//            } else {
//                System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
//            }
//        });
//
//    }


    public void sendEventsToTopic(CustomerDto customerDto){
        try {
            CompletableFuture<SendResult<String, Object>> future = template.send("customer-topic", customerDto);
            future.whenComplete((result,ex)->{
                if (ex == null) {
                    System.out.println("Sent message=[" + customerDto.toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" + customerDto.toString() + "] due to : " + ex.getMessage());
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


}
