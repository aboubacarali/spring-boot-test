package fr.m2i.springtest.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException (String message) {
        super(message);
    }
}
