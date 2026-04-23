package com.m1.biblioteca.repository;

import com.m1.biblioteca.model.Atendimento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendimentoRepository extends MongoRepository<Atendimento, String> {
}