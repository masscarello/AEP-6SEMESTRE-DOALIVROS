package com.AEP.DoaLivros.controllers;

import com.AEP.DoaLivros.models.LivroModel;
import com.AEP.DoaLivros.services.LivroService;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LivroControllerTest {

    @Mock
    private LivroService livroService;

    @InjectMocks
    private LivroController livroController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void deveCadastrarLivroERetornarOk() {
        LivroModel livro = new LivroModel();
        livro.setTggitulo("Machado de Assis");
        when(livroService.cadastrar(livro)).thenReturn(livro);

        ResponseEntity<LivroModel> resposta = livroController.cadastrar(livro);

        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals("Machado de Assis", resposta.getBody().getTitulo());
        verify(livroService, times(1)).cadastrar(livro);
    }

    @Test
    void deveListarTodosOsLivrosERetornarOk() {
        LivroModel livro = new LivroModel();
        livro.setTitulo("Dom Casmurro");
        when(livroService.listarTodos()).thenReturn(List.of(livro));

        ResponseEntity<List<LivroModel>> resposta = livroController.listarTodos();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(1, resposta.getBody().size());
        assertEquals("Dom Casmurro", resposta.getBody().get(0).getTitulo());
    }

    @Test
    void deveListarLivrosDisponiveisERetornarOk() {
        LivroModel livro = new LivroModel();
        livro.setTitulo("O Cortiço");
        livro.setDisponivel(true);
        when(livroService.listarDisponiveis()).thenReturn(List.of(livro));

        ResponseEntity<List<LivroModel>> resposta = livroController.listarDisponiveis();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(1, resposta.getBody().size());
        assertTrue(resposta.getBody().get(0).isDisponivel());
        verify(livroService, times(1)).listarDisponiveis();
    }

    @Test
    void deveBuscarLivroPorIdERetornarOkQuandoExiste() {
        LivroModel livro = new LivroModel();
        livro.setId("1");
        livro.setTitulo("Memórias Póstumas");
        when(livroService.buscarPorId("1")).thenReturn(Optional.of(livro));

        ResponseEntity<?> resposta = livroController.buscarPorId("1");

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(livro, resposta.getBody());
    }

    @Test
    void deveRetornarNotFoundQuandoLivroNaoExiste() {
        when(livroService.buscarPorId("1")).thenReturn(Optional.empty());

        ResponseEntity<?> resposta = livroController.buscarPorId("1");

        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
        assertNull(resposta.getBody());
    }

    @Test
    void deveDeletarLivroERetornarNoContent() {
        ResponseEntity<Void> resposta = livroController.deletar("1");

        assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
        verify(livroService, times(1)).deletar("1");
    }
}