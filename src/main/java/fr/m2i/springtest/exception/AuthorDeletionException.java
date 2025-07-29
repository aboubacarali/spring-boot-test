package fr.m2i.springtest.exception;

public class AuthorDeletionException extends RuntimeException {
    public AuthorDeletionException(String message) {
        super(message);
    }
}