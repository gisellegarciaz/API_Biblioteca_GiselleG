package org.serratec.h2biblioteca.controller;

import org.serratec.h2biblioteca.domain.Livro;
import org.serratec.h2biblioteca.exception.RecursoNaoEncontradoException;
import org.serratec.h2biblioteca.repository.ILivroRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private ILivroRepository livroRepository;

    
    
    @GetMapping
    public ResponseEntity<List<Livro>> listar() {
        return ResponseEntity.ok(livroRepository.findAll());
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscar(@PathVariable Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Livro não encontrado com o id: " + id));
        return ResponseEntity.ok(livro);
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Livro inserir(@Valid @RequestBody Livro livro) {
        return livroRepository.save(livro);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @Valid @RequestBody Livro livro) {
        if (!livroRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Livro não encontrado com o id: " + id);
        }
        livro.setId(id);
        livro = livroRepository.save(livro);
        return ResponseEntity.ok(livro);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!livroRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Livro não encontrado com o id: " + id);
        }
        livroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}