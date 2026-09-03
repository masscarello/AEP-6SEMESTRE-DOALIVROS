package com.AEP.DoaLivros.repositories;

import com.AEP.DoaLivros.models.UsuarioModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<UsuarioModel,String> {
}
