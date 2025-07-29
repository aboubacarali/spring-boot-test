package fr.m2i.springtest.repository;

import fr.m2i.springtest.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository {
    Optional<Book> findByTitle(String title);

    // Compte le nombre de livres pour un auteur donné
    long countByAuthorId(Long authorId);
}