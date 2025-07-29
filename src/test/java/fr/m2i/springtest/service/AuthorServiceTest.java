package fr.m2i.springtest.service;

import fr.m2i.springtest.entity.Author;
import fr.m2i.springtest.exception.AuthorDeletionException;
import fr.m2i.springtest.repository.AuthorRepository;
import fr.m2i.springtest.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class AuthorServiceTest {

    @MockitoBean
    private BookRepository bookRepository;
    @MockitoBean
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorService authorService;

    @Test
    public void shouldAuthorDeletionException () {
        Author author = new Author(3L, "Albert Camus");
        when(bookRepository.countByAuthorId(author.getId())).thenReturn(4L);

        assertThrows(AuthorDeletionException.class, () -> authorService.deleteAuthor(author.getId()));

    }

    @Test
    public void shouldDeleteAuthorIfNoBookFound() {
        Author author = new Author(1L, "Albert Camus");
        when(bookRepository.countByAuthorId(author.getId())).thenReturn(0L);
        authorService.deleteAuthor(author.getId());
        verify(authorRepository).deleteById(author.getId());

    }

}