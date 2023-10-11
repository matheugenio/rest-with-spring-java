package br.com.matheugenioti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.matheugenioti.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	
}
