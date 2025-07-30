package fr.m2i.springtest.controller;

import fr.m2i.springtest.dto.UpdateBookDto;
import fr.m2i.springtest.entity.Book;
import fr.m2i.springtest.exception.BookNotFoundException;
import fr.m2i.springtest.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    // Dans le BookController
//    @PutMapping("/{id}")
//    public ResponseEntity<Book> updateBook(@PathVariable Long id,
//                                           @Valid @RequestBody UpdateBookDto bookDto) {
//        try {
//            Book updatedBook = bookService.update(id, bookDto);
//            return ResponseEntity.ok(updatedBook);
//        } catch (BookNotFoundException ex) {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
