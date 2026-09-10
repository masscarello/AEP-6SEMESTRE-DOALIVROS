package com.AEP.DoaLivros.controllers;

import com.AEP.DoaLivros.models.PedidoModel;
import com.AEP.DoaLivros.services.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController pedidoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarPedidoERetornar201() {
        PedidoModel pedido = new PedidoModel();
        pedido.setIdLivro("livro123");
        when(pedidoService.criar(pedido)).thenReturn(pedido);

        ResponseEntity<PedidoModel> resposta = pedidoController.criar(pedido);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals(pedido, resposta.getBody());
    }

    @Test
    void deveListarTodosOsPedidos() {
        PedidoModel pedido = new PedidoModel();
        when(pedidoService.listarTodos()).thenReturn(List.of(pedido));

        ResponseEntity<List<PedidoModel>> resposta = pedidoController.listarTodos();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(1, resposta.getBody().size());
    }

    @Test
    void deveRetornarPedidoPorId() {
        PedidoModel pedido = new PedidoModel();
        when(pedidoService.buscarPorId("1")).thenReturn(Optional.of(pedido));

        ResponseEntity<PedidoModel> resposta = pedidoController.buscarPorId("1");

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void deveRetornar404QuandoPedidoNaoExiste() {
        when(pedidoService.buscarPorId("999")).thenReturn(Optional.empty());

        ResponseEntity<PedidoModel> resposta = pedidoController.buscarPorId("999");

        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
    }

    @Test
    void deveCancelarPedido() {
        PedidoModel pedido = new PedidoModel();
        when(pedidoService.cancelar("1")).thenReturn(Optional.of(pedido));

        ResponseEntity<PedidoModel> resposta = pedidoController.cancelar("1");

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void deveRetornar404AoCancelarPedidoInexistente() {
        when(pedidoService.cancelar("999")).thenReturn(Optional.empty());

        ResponseEntity<PedidoModel> resposta = pedidoController.cancelar("999");

        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
    }
}