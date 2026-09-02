package com.AEP.DoaLivros.repositories;

import com.AEP.DoaLivros.models.LivroModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LivroRepository extends MongoRepository<LivroModel, String> {

    List<LivroModel> findByDisponivelTrue();
    List<LivroModel> findByIdCategoria(String categoria);
}
