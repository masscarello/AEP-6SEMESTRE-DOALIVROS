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

    public PedidoModel() {
    }

    public PedidoModel(String idLivro, String idUsuario) {
        this.idLivro = idLivro;
        this.idUsuario = idUsuario;
        this.status = StatusPedido.PENDENTE;
        this.dataPedido = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(String idLivro) {
        this.idLivro = idLivro;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public enum StatusPedido {
        PENDENTE,
        APROVADO,
        CANCELADO,
        CONCLUIDO
    }
}
