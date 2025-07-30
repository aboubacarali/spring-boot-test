package fr.m2i.springtest.service;

import fr.m2i.springtest.dto.UpdateBookDto;
import fr.m2i.springtest.entity.Book;
import fr.m2i.springtest.repository.BookRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;


}