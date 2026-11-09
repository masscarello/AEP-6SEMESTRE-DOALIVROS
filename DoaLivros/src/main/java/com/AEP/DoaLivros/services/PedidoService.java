package com.AEP.DoaLivros.services;

import com.AEP.DoaLivros.models.LivroModel;
import com.AEP.DoaLivros.models.PedidoModel;
import com.AEP.DoaLivros.repositories.LivroRepository;
import com.AEP.DoaLivros.repositories.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final LivroRepository livroRepository;

    public PedidoService(PedidoRepository pedidoRepository, LivroRepository livroRepository) {
        this.pedidoRepository = pedidoRepository;
        this.livroRepository = livroRepository;
    }

    public PedidoModel criar(PedidoModel pedido) {
        pedido.setStatus(PedidoModel.StatusPedido.PENDENTE);
        pedido.setDataPedido(LocalDateTime.now());

        // Popula o snapshot do livro no momento do pedido
        if (pedido.getIdLivro() != null) {
            livroRepository.findById(pedido.getIdLivro()).ifPresent(livro -> {
                PedidoModel.LivroSnapshot snapshot = new PedidoModel.LivroSnapshot(
                        livro.getTitulo(),
                        livro.getAutor(),
                        livro.getCategoria(),
                        livro.getConservacao()
                );
                pedido.setLivroSnapshot(snapshot);
            });
        }

        return pedidoRepository.save(pedido);
    }

    public List<PedidoModel> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<PedidoModel> buscarPorId(String id) {
        return pedidoRepository.findById(id);
    }

    public Optional<PedidoModel> cancelar(String id) {
        Optional<PedidoModel> pedidoExistente = pedidoRepository.findById(id);
        pedidoExistente.ifPresent(pedido -> {
            pedido.setStatus(PedidoModel.StatusPedido.CANCELADO);
            pedidoRepository.save(pedido);
        });
        return pedidoExistente;
    }
}
