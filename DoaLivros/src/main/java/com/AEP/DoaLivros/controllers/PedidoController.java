package com.AEP.DoaLivros.controllers;

import com.AEP.DoaLivros.models.PedidoModel;
import com.AEP.DoaLivros.models.PedidoModel;
import com.AEP.DoaLivros.services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<PedidoModel>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoModel> buscarPorId(@PathVariable String id) {
        return pedidoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PedidoModel> criar(@RequestBody PedidoModel pedido) {
        return ResponseEntity.status(201).body(pedidoService.criar(pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PedidoModel> cancelar(@PathVariable String id) {
        return pedidoService.cancelar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
