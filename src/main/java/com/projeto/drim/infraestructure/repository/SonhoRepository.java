package com.projeto.drim.infraestructure.repository;

import com.projeto.drim.infraestructure.entity.Sonho.SonhoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SonhoRepository extends MongoRepository<SonhoEntity, String> {

}
