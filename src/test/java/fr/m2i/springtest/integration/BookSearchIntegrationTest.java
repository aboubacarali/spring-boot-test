package fr.m2i.springtest.integration;

import fr.m2i.springtest.entity.Book;
import fr.m2i.springtest.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BookSearchIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void shouldReturnBookById() {
        Book book = new Book();
        book.setTitle("Book Title");
        book.setAuthor("Book Author");
        book.setBestSeller(false);
        bookRepository.save(book);

        ResponseEntity<Book> response = restTemplate.getForEntity(
                "/books/{id}",
                Book.class,
                book.getId()
                );

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        Assertions.assertNotNull(response.getBody());
        assertThat(response.getBody().getTitle()).isEqualTo("Book Title");
    }
}
