package com.projeto.drim.infraestructure.repository;

import com.projeto.drim.infraestructure.entity.Usuario.UsuarioEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<UsuarioEntity, String> {



}
