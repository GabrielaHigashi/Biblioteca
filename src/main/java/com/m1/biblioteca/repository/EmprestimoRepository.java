package com.m1.biblioteca.repository;

import com.m1.biblioteca.model.Emprestimo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends MongoRepository<Emprestimo, String> {
}