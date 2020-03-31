package com.tecnositaf.centrobackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tecnositaf.centrobackend.model.Alert;

@Repository
public interface AlertRepository extends MongoRepository<Alert, String> {
}
