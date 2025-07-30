package fr.m2i.springtest.controller;

import fr.m2i.springtest.entity.Book;
import fr.m2i.springtest.exception.BookNotFoundException;
import fr.m2i.springtest.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;


 @GetMapping("/{id}")
 public ResponseEntity<Book> getBookById(@PathVariable Long id){
     try {
         Book book = bookService.getBookById(id);
         return ResponseEntity.ok().body(book);
     }
     catch (BookNotFoundException exception) {
         return ResponseEntity.notFound().build();
     }
 }
}
