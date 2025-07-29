package fr.m2i.springtest.service;

import fr.m2i.springtest.exception.AuthorDeletionException;
import fr.m2i.springtest.repository.AuthorRepository;
import fr.m2i.springtest.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public void deleteAuthor(Long authorId) {
        long bookCount = bookRepository.countByAuthorId(authorId);
        if (bookCount > 0) {
            throw new AuthorDeletionException(
                    "Impossible de supprimer l'auteur, il a encore " +
                            bookCount + " livre(s) publié(s)."
            );
        }
        authorRepository.deleteById(authorId);
    }
}