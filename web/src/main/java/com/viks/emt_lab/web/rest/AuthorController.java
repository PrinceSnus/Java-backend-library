package com.viks.emt_lab.web.rest;

import com.viks.emt_lab.model.Book;
import com.viks.emt_lab.model.dto.AuthorDTO;
import com.viks.emt_lab.model.Author;
import com.viks.emt_lab.service.AuthorService;
import com.viks.emt_lab.service.BookService;
import com.viks.emt_lab.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin (origins = "http://localhost:3000")
@RequestMapping("/")
public class AuthorController {
  private final AuthorService authorService;
  private final CountryService countryService;
  private final BookService bookService;

  @Autowired
  public AuthorController(AuthorService authorService, CountryService countryService, BookService bookService) {
    this.authorService = authorService;
    this.countryService = countryService;
    this.bookService = bookService;
  }

  @GetMapping("/authors")
  public List<Author> getAuthors(){
    return this.authorService.getAllAuthors();
  }

  @GetMapping("/author/{authorId}")
  public Author getAuthor(@PathVariable Long authorId){
    if (authorId == null){
        ResponseEntity.notFound().build();
    }
    return this.authorService.findById(authorId);
  }
  @DeleteMapping("/author/{authorId}/delete")
  public ResponseEntity<Void> deleteAuthor(@PathVariable Long authorId) {
    if (authorId == null) {
      return ResponseEntity.notFound().build();
    }

    Author authorToDelete = this.authorService.findById(authorId);
    if (authorToDelete == null) {
      return ResponseEntity.notFound().build();
    }

    List<Book> books = bookService.getAllBooks();
    for (Book book : books) {
      List<Author> authors = book.getAuthor();
      if (authors != null && authors.contains(authorToDelete)) {
        authors.remove(authorToDelete);
      }
    }

    this.authorService.deleteAuthor(authorToDelete);
    return ResponseEntity.ok().build();
  }
  @PutMapping("/add/author")
  public ResponseEntity<Void> addAuthor(@RequestBody AuthorDTO authorDTO){
    if (authorDTO == null){
      ResponseEntity.notFound().build();
    }
    this.authorService.addAuthor(authorDTO);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/author/edit/{authorId}")
  public ResponseEntity<Void> editAuthor(@PathVariable Long authorId, @RequestBody AuthorDTO authorDTO) {
    if (authorDTO == null) {
      return ResponseEntity.notFound().build();
    }

    this.authorService.updateAuthor(authorId, authorDTO);
    return ResponseEntity.ok().build();
  }
}
