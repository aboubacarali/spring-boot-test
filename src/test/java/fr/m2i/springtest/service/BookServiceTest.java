package fr.m2i.springtest.service;

import fr.m2i.springtest.entity.Book;
import fr.m2i.springtest.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTest {

    @MockitoBean
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;




    @Test
    public void descriptionShouldContainBestSeller() {
        Book book =  new Book("Arsène Lupin", "Maurice Leblanc", true);
        when(bookRepository.findByTitle(book.getTitle())).thenReturn(Optional.of(book));

        assert(bookService.getBookDescription(book.getTitle()).contains("[BEST-SELLER]"));

    }


}