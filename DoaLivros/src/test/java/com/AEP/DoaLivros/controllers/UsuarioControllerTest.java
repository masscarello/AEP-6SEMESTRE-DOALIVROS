package com.AEP.DoaLivros.controllers;

import com.AEP.DoaLivros.models.UsuarioModel;
import com.AEP.DoaLivros.services.UsuarioService;
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
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void deveCriarUsuarioERetornarOk() {
        UsuarioModel usuario = new UsuarioModel("Maria Silva", "maria@email.com", "123456");
        when(usuarioService.criarUsuario(usuario)).thenReturn(usuario);

        ResponseEntity<UsuarioModel> resposta = usuarioController.criarUsuario(usuario);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals("Maria Silva", resposta.getBody().getNome());
        verify(usuarioService, times(1)).criarUsuario(usuario);
    }

    @Test
    void deveListarUsuariosERetornarOk() {
        UsuarioModel usuario = new UsuarioModel("João Souza", "joao@email.com", "abcdef");
        when(usuarioService.listarUsuarios()).thenReturn(List.of(usuario));

        ResponseEntity<List<UsuarioModel>> resposta = usuarioController.listarUsuarios();

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(1, resposta.getBody().size());
        assertEquals("João Souza", resposta.getBody().get(0).getNome());
    }

    @Test
    void deveBuscarUsuarioPorIdERetornarOkQuandoExiste() {
        UsuarioModel usuario = new UsuarioModel("Ana Lima", "ana@email.com", "senha123");
        usuario.setId("1");
        when(usuarioService.buscarPorId("1")).thenReturn(Optional.of(usuario));

        ResponseEntity<UsuarioModel> resposta = usuarioController.buscarPorId("1");

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals("Ana Lima", resposta.getBody().getNome());
    }

    @Test
    void deveRetornarNotFoundQuandoUsuarioNaoExiste() {
        when(usuarioService.buscarPorId("1")).thenReturn(Optional.empty());

        ResponseEntity<UsuarioModel> resposta = usuarioController.buscarPorId("1");

        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
        assertNull(resposta.getBody());
    }

    @Test
    void deveAtualizarUsuarioERetornarOk() {
        UsuarioModel usuario = new UsuarioModel("Carlos Alves", "carlos@email.com", "novaSenha");
        when(usuarioService.atualizarUsuario(any(String.class), any(UsuarioModel.class))).thenReturn(usuario);

        ResponseEntity<UsuarioModel> resposta = usuarioController.atualizarUsuario("1", usuario);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals("Carlos Alves", resposta.getBody().getNome());
        verify(usuarioService, times(1)).atualizarUsuario("1", usuario);
    }

    @Test
    void deveDeletarUsuarioERetornarNoContent() {
        ResponseEntity<Void> resposta = usuarioController.deletarUsuario("1");

        assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
        verify(usuarioService, times(1)).deletarUsuario("1");
    }
}