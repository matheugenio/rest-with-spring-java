package br.com.matheugenioti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.matheugenioti.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {}
