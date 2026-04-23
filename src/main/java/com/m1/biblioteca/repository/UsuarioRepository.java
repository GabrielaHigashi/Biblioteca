package com.m1.biblioteca.repository;

import com.m1.biblioteca.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByRegistro(String registro);
    Optional<Usuario> findByCpf(String cpf);
}