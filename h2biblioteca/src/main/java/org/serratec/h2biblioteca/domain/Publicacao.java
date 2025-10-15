package org.serratec.h2biblioteca.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Embeddable
public class Publicacao {

    @NotEmpty(message = "O nome do autor não pode ser vazio.")
    @Size(max = 25, message = "O nome do autor não pode exceder 25 caracteres.")
    private String autor;

    private LocalDate dataPublicacao;
    private String editora;

    
    
    
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }
    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
    public String getEditora() {
        return editora;
    }
    public void setEditora(String editora) {
        this.editora = editora;
    }
}