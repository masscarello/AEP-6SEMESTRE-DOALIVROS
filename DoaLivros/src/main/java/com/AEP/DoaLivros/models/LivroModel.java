package com.AEP.DoaLivros.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "livros")
public class LivroModel {

    @Id
    private String id;

    private String titulo;
    private String autor;
    private String categoria;
    private String conservacao;
    private String disponivel;
    private String idDoador;
}
