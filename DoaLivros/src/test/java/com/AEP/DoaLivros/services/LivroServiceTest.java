package com.AEP.DoaLivros.services;

import com.AEP.DoaLivros.models.LivroModel;
import com.AEP.DoaLivros.repositories.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class LivroServiceTest {

    @Mock
    private LivroRepository livroRepository;

    @InjectMocks
    private LivroService livroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void deveCadastrarLivroComoDisponivel() {
        LivroModel livro = new LivroModel();
        livro.setTitulo("Machado de Assis");
        when(livroRepository.save(livro)).thenReturn(livro);

        LivroModel resultado = livroService.cadastrar(livro);

        assertTrue(resultado.isDisponivel());
        verify(livroRepository, times(1)).save(livro);
    }

    @Test
    void deveListarTodosOsLivros() {
        LivroModel livro = new LivroModel();
        when(livroRepository.findAll()).thenReturn(List.of(livro));

        List<LivroModel> resultado = livroService.listarTodos();

        assertEquals(1, resultado.size());
        verify(livroRepository, times(1)).findAll();
    }

    @Test
    void deveRetornarLivroPorId() {
        LivroModel livro = new LivroModel();
        livro.setTitulo("Machado de Assis");
        when(livroRepository.findById("1")).thenReturn(Optional.of(livro));

        Optional<LivroModel> resultado = livroService.buscarPorId("1");

        assertTrue(resultado.isPresent());
        assertEquals("Machado de Assis", resultado.get().getTitulo());
    }

    @Test
    void deveRetornarVazioQunadoLivroNaoExiste() {
        when(livroRepository.findById("1")).thenReturn(Optional.empty());

        Optional<LivroModel> resultado = livroService.buscarPorId("1");

        assertFalse(resultado.isPresent());
    }

    @Test
    void deveDeletarLivro() {
        livroService.deletar("1");
        verify(livroRepository, times(1)).deleteById("1");
    }
}
