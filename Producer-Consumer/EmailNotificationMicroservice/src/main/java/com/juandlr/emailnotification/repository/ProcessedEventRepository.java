package com.juandlr.emailnotification.repository;

import com.juandlr.emailnotification.entity.ProcessedEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessedEventRepository extends JpaRepository<ProcessedEventEntity, Long> {
    Optional<ProcessedEventEntity> findByProductId(String productId);

}
