package com.AEP.DoaLivros.services;

import com.AEP.DoaLivros.models.PedidoModel;
import com.AEP.DoaLivros.repositories.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarPedidoComStatusPendente() {
        PedidoModel pedido = new PedidoModel();
        pedido.setIdLivro("livro123");
        pedido.setIdUsuario("usuario123");
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        PedidoModel resultado = pedidoService.criar(pedido);

        assertEquals(PedidoModel.StatusPedido.PENDENTE, resultado.getStatus());
        assertNotNull(resultado.getDataPedido());
        verify(pedidoRepository, times(1)).save(pedido);
    }

    @Test
    void deveListarTodosOsPedidos() {
        PedidoModel pedido = new PedidoModel();
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));

        List<PedidoModel> resultado = pedidoService.listarTodos();

        assertEquals(1, resultado.size());
        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    void deveRetornarPedidoPorId() {
        PedidoModel pedido = new PedidoModel();
        pedido.setIdLivro("livro123");
        when(pedidoRepository.findById("1")).thenReturn(Optional.of(pedido));

        Optional<PedidoModel> resultado = pedidoService.buscarPorId("1");

        assertTrue(resultado.isPresent());
        assertEquals("livro123", resultado.get().getIdLivro());
    }

    @Test
    void deveRetornarVazioQuandoPedidoNaoExiste() {
        when(pedidoRepository.findById("999")).thenReturn(Optional.empty());

        Optional<PedidoModel> resultado = pedidoService.buscarPorId("999");

        assertFalse(resultado.isPresent());
    }

    @Test
    void deveCancelarPedido() {
        PedidoModel pedido = new PedidoModel();
        pedido.setStatus(PedidoModel.StatusPedido.PENDENTE);
        when(pedidoRepository.findById("1")).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        Optional<PedidoModel> resultado = pedidoService.cancelar("1");

        assertTrue(resultado.isPresent());
        assertEquals(PedidoModel.StatusPedido.CANCELADO, resultado.get().getStatus());
    }

    @Test
    void deveRetornarVazioAoCancelarPedidoInexistente() {
        when(pedidoRepository.findById("999")).thenReturn(Optional.empty());

        Optional<PedidoModel> resultado = pedidoService.cancelar("999");

        assertFalse(resultado.isPresent());
    }
}