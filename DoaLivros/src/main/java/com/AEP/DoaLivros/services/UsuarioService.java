package com.AEP.DoaLivros.services;

import com.AEP.DoaLivros.models.UsuarioModel;
import com.AEP.DoaLivros.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

   public UsuarioModel criarUsuario(UsuarioModel usuarioModel){
        return usuarioRepository.save(usuarioModel);
   }

   public List<UsuarioModel> listarUsuarios(){
        return usuarioRepository.findAll();
   }
   public Optional<UsuarioModel> buscarPorId(String id){
        return usuarioRepository.findById(id);
   }
   public void deletarUsuario(String id) {
       usuarioRepository.deleteById(id);
   }
    public UsuarioModel atualizarUsuario(String id, UsuarioModel usuario) {
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }


}
