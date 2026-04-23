package com.m1.biblioteca.repository;

import com.m1.biblioteca.model.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReservaRepository extends MongoRepository<Reserva, String> {
    
}