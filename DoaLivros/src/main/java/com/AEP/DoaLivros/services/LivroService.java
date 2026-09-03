package com.AEP.DoaLivros.services;

import com.AEP.DoaLivros.models.LivroModel;
import com.AEP.DoaLivros.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    public LivroModel cadastrar(LivroModel livro) {
        livro.setDisponivel(true);
        return livroRepository.save(livro);
    }

    public List<LivroModel> listarTodos() {
        return livroRepository.findAll();
    }

    public List<LivroModel> listarDisponiveis() {
        return livroRepository.findByDisponivelTrue();
    }

    public Optional<LivroModel> buscarPorId(String id) {
        return livroRepository.findById(id);
    }

    public void deletar(String id) {
        livroRepository.deleteById(id);
    }
}
