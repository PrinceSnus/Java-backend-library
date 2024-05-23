
package com.viks.emt_lab.web.rest;

import com.viks.emt_lab.model.Author;
import com.viks.emt_lab.model.dto.AuthorDTO;
import com.viks.emt_lab.model.dto.BookDTO;
import com.viks.emt_lab.model.Book;
import com.viks.emt_lab.service.AuthorService;
import com.viks.emt_lab.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin (origins = "http://localhost:3000")
@RequestMapping("/")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/books")
    public List<Book> getBooks(){
        return this.bookService.getAllBooks();
    }

    @GetMapping("/book/{bookId}")
    public Book getBook(@PathVariable Long bookId){
        if (bookId == null){
            ResponseEntity.notFound().build();
        }
        return this.bookService.findById(bookId);
    }
    @DeleteMapping("/book/{bookId}/delete")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId){
        if (bookId == null){
            ResponseEntity.notFound().build();
        }
        Book bookToDelete=this.bookService.findById(bookId);
        this.bookService.findById(bookId).setAuthor(null);
        this.bookService.deleteBook(bookToDelete);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/add/book")
    public ResponseEntity<Void> addBook(@RequestBody BookDTO bookDTO){
        if (bookDTO == null){
            ResponseEntity.notFound().build();
        }
        List<Author> authors = new ArrayList<>();
        if (bookDTO.getAuthor() != null) {
        authors.add(this.authorService.findById(bookDTO.getAuthor()));
        }
        this.bookService.addBook(bookDTO.getName(), bookDTO.getCategory(), authors, bookDTO.getAvailableCopies());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/book/{bookId}/take")
        public ResponseEntity<Void> takeBook(@PathVariable Long bookId){
            if (bookId==null){
                return ResponseEntity.notFound().build();
            }
            Book book = this.bookService.findById(bookId);
            if (book.getAvailableCopies()==0){
                return ResponseEntity.badRequest().build();
            }
            this.bookService.updateBook(book.getId(),book.getName(), book.getCategory(), book.getAuthor(), (book.getAvailableCopies()-1));
            return ResponseEntity.ok().build();
        }

    @PutMapping("/book/{bookId}/edit")
    public ResponseEntity<Void> editAuthor(@PathVariable Long bookId, @RequestBody BookDTO bookDTO) {
        if (bookDTO == null) {
            return ResponseEntity.notFound().build();
        }
        this.bookService.updateBook(bookId, bookDTO);
        return ResponseEntity.ok().build();
    }
}