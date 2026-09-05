package com.AEP.DoaLivros.services;

import com.AEP.DoaLivros.models.UsuarioModel;
import com.AEP.DoaLivros.repositories.UsuarioRepository;
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
import static org.mockito.Mockito.any;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void deveCriarUsuario() {
        UsuarioModel usuario = new UsuarioModel("Maria Silva", "maria@email.com", "123456");
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        UsuarioModel resultado = usuarioService.criarUsuario(usuario);

        assertNotNull(resultado);
        assertEquals("Maria Silva", resultado.getNome());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void deveListarTodosOsUsuarios() {
        UsuarioModel usuario = new UsuarioModel("João Souza", "joao@email.com", "abcdef");
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<UsuarioModel> resultado = usuarioService.listarUsuarios();

        assertEquals(1, resultado.size());
        assertEquals("João Souza", resultado.get(0).getNome());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void deveRetornarUsuarioPorId() {
        UsuarioModel usuario = new UsuarioModel("Ana Lima", "ana@email.com", "senha123");
        usuario.setId("1");
        when(usuarioRepository.findById("1")).thenReturn(Optional.of(usuario));

        Optional<UsuarioModel> resultado = usuarioService.buscarPorId("1");

        assertTrue(resultado.isPresent());
        assertEquals("Ana Lima", resultado.get().getNome());
        assertEquals("ana@email.com", resultado.get().getEmail());
    }

    @Test
    void deveRetornarVazioQuandoUsuarioNaoExiste() {
        when(usuarioRepository.findById("1")).thenReturn(Optional.empty());

        Optional<UsuarioModel> resultado = usuarioService.buscarPorId("1");

        assertFalse(resultado.isPresent());
    }

    @Test
    void deveDeletarUsuario() {
        usuarioService.deletarUsuario("1");
        verify(usuarioRepository, times(1)).deleteById("1");
    }

    @Test
    void deveAtualizarUsuario() {
        UsuarioModel usuario = new UsuarioModel("Carlos Alves", "carlos@email.com", "novaSenha");
        when(usuarioRepository.save(any(UsuarioModel.class))).thenReturn(usuario);

        UsuarioModel resultado = usuarioService.atualizarUsuario("1", usuario);

        assertEquals("1", usuario.getId());
        assertEquals("Carlos Alves", resultado.getNome());
        verify(usuarioRepository, times(1)).save(usuario);
    }
}