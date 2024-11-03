package com.juandlr.kafkaconsumer.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private int id;

    private String name;

    private String lastName;

    private String email;
}
