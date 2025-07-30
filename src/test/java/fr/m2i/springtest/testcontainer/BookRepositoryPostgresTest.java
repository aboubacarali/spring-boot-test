package fr.m2i.springtest.testcontainer;


import fr.m2i.springtest.entity.Book;
import fr.m2i.springtest.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers // 1. Active Testcontainers
// On désactive le remplacement par la BDD embarquée, on gère nous-mêmes.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryPostgresTest {

    @Container // 2. Déclare un conteneur qui sera géré par Testcontainers
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );

    @Autowired
    private BookRepository bookRepository;

    // 3. Fournit les propriétés de connexion dynamiquement à Spring
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.driver-class-name",
                () -> "org.postgresql.Driver"); // <--- Ajout obligatoire
        registry.add("spring.jpa.database-platform",
                () -> "org.hibernate.dialect.PostgreSQLDialect");
    }

    @Test
    void whenSaved_thenCanBeFound() {
        // Arrange
        Book book = new Book();
        book.setTitle("Hyperion");
        book.setAuthor("Dan Simmons");
        book.setBestSeller(true);

        // Act
        bookRepository.save(book);

        // Assert
        Book found = bookRepository.findByTitle("Hyperion").orElseThrow();
        assertThat(found.getAuthor()).isEqualTo("Dan Simmons");
    }
}
