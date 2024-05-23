package com.viks.emt_lab.service.impl;

import com.viks.emt_lab.model.Author;
import com.viks.emt_lab.model.Book;
import com.viks.emt_lab.model.Category;
import com.viks.emt_lab.model.dto.BookDTO;
import com.viks.emt_lab.model.exceptions.InvalidAuthorIdException;
import com.viks.emt_lab.model.exceptions.InvalidBookIdException;
import com.viks.emt_lab.repository.AuthorRepository;
import com.viks.emt_lab.repository.BookRepository;
import com.viks.emt_lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Book findById(Long id) {
        return this.bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
    }

    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book addBook(String name, Category category, List<Author> author, Integer availableCopies) {
        Book book = new Book(name, category, author, availableCopies);
        return this.bookRepository.save(book);
    }

    public Optional<Book> addBook(BookDTO bookDTO) {
        Optional<Author> authorOptional = this.authorRepository.findById(bookDTO.getAuthor());
            Author author = authorOptional.get();
            List<Author> authors = new ArrayList<>();
            authors.add(author);
            Book book = new Book(bookDTO.getName(), bookDTO.getCategory(), authors, bookDTO.getAvailableCopies());
            return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void deleteBook(Book book) {
        this.bookRepository.delete(book);
    }

    @Override
    public Book updateBook(Long id, String name, Category category, List<Author> author, Integer availableCopies) {
        Book bookToUpdate = findById(id);
        bookToUpdate.setName(name);
        bookToUpdate.setCategory(category);
        bookToUpdate.setAuthor(author);
        bookToUpdate.setAvailableCopies(availableCopies);
        return this.bookRepository.save(bookToUpdate);
    }
    @Override
    public Book updateBook(Long id, BookDTO bookDTO) {
        Book bookToUpdate = this.bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
        Author author = this.authorRepository.findById(bookDTO.getAuthor()).orElseThrow(InvalidAuthorIdException::new);
        bookToUpdate.setName(bookDTO.getName());
        bookToUpdate.getAuthor().add(author);
        bookToUpdate.setCategory(bookDTO.getCategory());
        bookToUpdate.setAvailableCopies(bookDTO.getAvailableCopies());
        return this.bookRepository.save(bookToUpdate);
    }

    @Override
    public void markBookAsRented(Long id) {
        Book book = findById(id);
        int availableCopies = book.getAvailableCopies();
        if (availableCopies > 0) {
            book.setAvailableCopies(availableCopies - 1);
            this.bookRepository.save(book);
        } else {
            throw new IllegalStateException("0");
        }
    }
}
