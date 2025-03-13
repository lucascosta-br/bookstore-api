package br.com.sousinhacode.repository;

import br.com.sousinhacode.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
