package fr.m2i.springtest.service;

import fr.m2i.springtest.dto.UpdateBookDto;
import fr.m2i.springtest.entity.Book;
import fr.m2i.springtest.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String getBookDescription(String title) {
        Optional<Book> bookOpt = bookRepository.findByTitle(title);
        if (bookOpt.isEmpty()) {
            return "Livre non trouvé.";
        }
        Book book = bookOpt.get();
        String description = book.getTitle() + " par " + book.getAuthor();
        if (book.isBestSeller()) {
            description += " [BEST-SELLER]";
        }
        return description;
    }


    public Book update(Long id, @Valid UpdateBookDto bookDto) {
        Optional<Book> bookToUpdate = bookRepository.findById(id);
        bookToUpdate.ifPresent(book -> book.setTitle(bookDto.getTitle()));
        return bookRepository.save(bookToUpdate );
    }
}