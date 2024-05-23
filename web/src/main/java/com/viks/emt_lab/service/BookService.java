package com.viks.emt_lab.service;

import com.viks.emt_lab.model.Author;
import com.viks.emt_lab.model.Book;
import com.viks.emt_lab.model.Category;
import com.viks.emt_lab.model.dto.BookDTO;

import java.util.List;

public interface BookService {
    Book findById(Long id);
    List<Book> getAllBooks();
    Book addBook(String name, Category category, List<Author> author, Integer availableCopies);
    void deleteBook(Book book);
    Book updateBook(Long id, String name, Category category, List<Author> author, Integer availableCopies);
    Book updateBook(Long id, BookDTO bookDTO);

    void markBookAsRented(Long id);
}
