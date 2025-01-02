package com.juandlr.emailnotification.entity;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class ProcessedEventEntity implements Serializable {

    private static final long serialVersionUID = 2345789654123478951L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String productId;

    public ProcessedEventEntity() {
    }

    public ProcessedEventEntity(String productId) {
        this.productId = productId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
