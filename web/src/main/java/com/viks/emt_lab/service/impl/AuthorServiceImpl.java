package com.viks.emt_lab.service.impl;

import com.viks.emt_lab.model.Author;
import com.viks.emt_lab.model.Book;
import com.viks.emt_lab.model.Country;
import com.viks.emt_lab.model.dto.AuthorDTO;
import com.viks.emt_lab.model.exceptions.InvalidAuthorIdException;
import com.viks.emt_lab.model.exceptions.InvalidBookIdException;
import com.viks.emt_lab.repository.AuthorRepository;
import com.viks.emt_lab.repository.CountryRepository;
import com.viks.emt_lab.service.AuthorService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Author findById(Long id) {
        return this.authorRepository.findById(id).orElseThrow(InvalidAuthorIdException::new);
    }

    @Override
    public List<Author> getAllAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> addAuthor(String name, String surname, Country country) {
        Author author = new Author(name, surname, country);
        return Optional.of(this.authorRepository.save(author));
    }

    public Optional<Author> addAuthor(AuthorDTO authorDTO) {
        Optional<Country> countryOptional = this.countryRepository.findById(authorDTO.getCountry());
        Country country = countryOptional.get();
        Author author = new Author(authorDTO.getName(), authorDTO.getSurname(), country);
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public void deleteAuthor(Author author) {
        this.authorRepository.delete(author);
    }

    @Override
    public Optional<Author> updateAuthor(Long id, AuthorDTO authorDTO) {
        Author avtorcic= this.authorRepository.findById(id).orElseThrow(InvalidBookIdException::new);
        Country country = this.countryRepository.findById(authorDTO.getCountry()).orElseThrow(InvalidAuthorIdException::new);
        avtorcic.setName(authorDTO.getName());
        avtorcic.setSurname(authorDTO.getSurname());
        avtorcic.setCountry(country);
        return Optional.of(this.authorRepository.save(avtorcic));
    }

    @Override
    public Author findAuthorByName(String name) {
        Author optionalAuthor = authorRepository.findByName(name);
        return optionalAuthor;
    }

}
