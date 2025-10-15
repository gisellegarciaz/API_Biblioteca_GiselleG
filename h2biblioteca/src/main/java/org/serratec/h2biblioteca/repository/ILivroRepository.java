package org.serratec.h2biblioteca.repository;

import org.serratec.h2biblioteca.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ILivroRepository extends JpaRepository<Livro, Long> {
    
}
	




