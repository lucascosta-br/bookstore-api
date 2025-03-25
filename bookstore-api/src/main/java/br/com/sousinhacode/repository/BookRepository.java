package br.com.sousinhacode.repository;

import br.com.sousinhacode.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
