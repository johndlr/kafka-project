package com.juandlr.kafkaconsumer.consumer;

import com.juandlr.kafkaconsumer.dto.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {

    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(topics = "customer-topic", groupId = "ct-group")
    public void consumeEvents(CustomerDto customerDto){
        log.info("consumer consume the event {} ", customerDto.toString());
    }

//    @KafkaListener(topics = "demo-topic", groupId = "cm-group")
//    public void consume1(String message) {
//        log.info("consumer no. 1 consume the message {} ", message);
//    }
//
//    @KafkaListener(topics = "demo-topic", groupId = "cm-group")
//    public void consume2(String message) {
//        log.info("consumer no. 2 consume the message {} ", message);
//    }
//
//    @KafkaListener(topics = "demo-topic", groupId = "cm-group")
//    public void consume3(String message) {
//        log.info("consumer no. 3 consume the message {} ", message);
//    }
//
//    @KafkaListener(topics = "demo-topic", groupId = "cm-group")
//    public void consume4(String message) {
//        log.info("consumer no. 4 consume the message {} ", message);
//    }
}
