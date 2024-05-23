package com.viks.emt_lab.service;

import com.viks.emt_lab.model.Author;
import com.viks.emt_lab.model.Country;
import com.viks.emt_lab.model.dto.AuthorDTO;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author findById(Long id);
    List<Author> getAllAuthors();
    Optional<Author> addAuthor(String name, String surname, Country country);
    Optional<Author> addAuthor(AuthorDTO authorDTO);
    void deleteAuthor(Author author);
    Optional<Author> updateAuthor(Long id, AuthorDTO authorDTO);
    public Author findAuthorByName(String name);
}
