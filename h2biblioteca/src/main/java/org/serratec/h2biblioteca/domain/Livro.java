package org.serratec.h2biblioteca.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O título não pode ser vazio.")
    @Size(max = 40, message = "O título não pode exceder 40 caracteres.")
    private String titulo;

    @NotNull(message = "A quantidade de páginas não pode ser nula.")
    private Integer qtdPaginas;

    @Embedded
    @Valid
    private Publicacao publicacao;


    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Integer getQtdPaginas() {
        return qtdPaginas;
    }
    public void setQtdPaginas(Integer qtdPaginas) {
        this.qtdPaginas = qtdPaginas;
    }
    public Publicacao getPublicacao() {
        return publicacao;
    }
    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }
}