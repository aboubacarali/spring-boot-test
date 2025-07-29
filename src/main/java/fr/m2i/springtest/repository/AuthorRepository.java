package fr.m2i.springtest.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository {

    public void deleteById (Long id);
}
