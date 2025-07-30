package fr.m2i.springtest.controller;

import fr.m2i.springtest.dto.UpdateBookDto;
import fr.m2i.springtest.entity.Book;
import fr.m2i.springtest.repository.BookRepository;
import fr.m2i.springtest.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookController bookController;

    @MockitoBean
    private BookService mockBookService;
    @MockitoBean
    private Book mockBook;

    @MockitoBean
    private BookRepository bookRepository;

    @Test
    void updateBook_shouldReturnUpdatedBook_whenSuccess() throws Exception {
        // Arrange
        Long bookId = 1L;
        UpdateBookDto updateDto = new UpdateBookDto("Nouveau Titre Super");
        Book updatedBook = new Book(bookId, "Nouveau Titre Super", "Nouveau auteur super", false);

        Book bookToUpdate = new Book(1L, "Titre super", "Auteur super", false);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookToUpdate));

        when(bookRepository.save(any())).thenReturn(updatedBook);

        // Configurer le mock du service
        // QUAND update est appelé avec l'ID 1 et n'importe quel DTO...
        when(mockBookService.update(eq(bookId), any(UpdateBookDto.class)))
                .thenReturn(updatedBook);

        // Act & Assert
        mockMvc.perform(put("/books/{id}", bookId) // Utilise une variable pour l'URL
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(bookId.intValue())))
                .andExpect(jsonPath("$.title", is("Nouveau Titre Super")));
    }

}