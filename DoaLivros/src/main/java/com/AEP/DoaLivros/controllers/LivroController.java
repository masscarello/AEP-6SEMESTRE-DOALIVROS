package com.AEP.DoaLivros.controllers;

import com.AEP.DoaLivros.models.LivroModel;
import com.AEP.DoaLivros.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class LivroController {
    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<LivroModel> cadastrar(LivroModel livro){
        return ResponseEntity.ok(livroService.cadastrar(livro));
    }

    @GetMapping
    public ResponseEntity<List<LivroModel>> listarTodos() {
        return ResponseEntity.ok(livroService.listarTodos());
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<LivroModel>> listarDisponiveis() {
        return ResponseEntity.ok(livroService.listarDisponiveis());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        Optional<LivroModel> livro = livroService.buscarPorId(id);
        if (livro.isPresent()) {
            return ResponseEntity.ok(livro.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
