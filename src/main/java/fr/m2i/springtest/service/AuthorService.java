package fr.m2i.springtest.service;

import fr.m2i.springtest.repository.AuthorRepository;
import fr.m2i.springtest.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

}