package com.AEP.DoaLivros.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "pedidos")
public class PedidoModel {

    @Id
    private String id;

    private String idLivro;
    private String idUsuario;
    private StatusPedido status;
    private LocalDateTime dataPedido;

    // Subdocumento: snapshot do livro no momento do pedido
    private LivroSnapshot livroSnapshot;

    public PedidoModel() {
    }

    public PedidoModel(String idLivro, String idUsuario) {
        this.idLivro = idLivro;
        this.idUsuario = idUsuario;
        this.status = StatusPedido.PENDENTE;
        this.dataPedido = LocalDateTime.now();
    }

    public PedidoModel(String idLivro, String idUsuario, LivroSnapshot livroSnapshot) {
        this.idLivro = idLivro;
        this.idUsuario = idUsuario;
        this.status = StatusPedido.PENDENTE;
        this.dataPedido = LocalDateTime.now();
        this.livroSnapshot = livroSnapshot;
    }

    // Classe interna — subdocumento aninhado no MongoDB
    public static class LivroSnapshot {
        private String titulo;
        private String autor;
        private String categoria;
        private String conservacao;

        public LivroSnapshot() {
        }

        public LivroSnapshot(String titulo, String autor, String categoria, String conservacao) {
            this.titulo = titulo;
            this.autor = autor;
            this.categoria = categoria;
            this.conservacao = conservacao;
        }

        public String getTitulo() { return titulo; }
        public void setTitulo(String titulo) { this.titulo = titulo; }

        public String getAutor() { return autor; }
        public void setAutor(String autor) { this.autor = autor; }

        public String getCategoria() { return categoria; }
        public void setCategoria(String categoria) { this.categoria = categoria; }

        public String getConservacao() { return conservacao; }
        public void setConservacao(String conservacao) { this.conservacao = conservacao; }
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getIdLivro() { return idLivro; }
    public void setIdLivro(String idLivro) { this.idLivro = idLivro; }

    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }

    public LocalDateTime getDataPedido() { return dataPedido; }
    public void setDataPedido(LocalDateTime dataPedido) { this.dataPedido = dataPedido; }

    public LivroSnapshot getLivroSnapshot() { return livroSnapshot; }
    public void setLivroSnapshot(LivroSnapshot livroSnapshot) { this.livroSnapshot = livroSnapshot; }

    public enum StatusPedido {
        PENDENTE,
        APROVADO,
        CANCELADO,
        CONCLUIDO
    }
}
