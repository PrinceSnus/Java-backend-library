package com.viks.emt_lab.bootstrap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.viks.emt_lab.model.Author;
import com.viks.emt_lab.model.Book;
import com.viks.emt_lab.model.Category;
import com.viks.emt_lab.model.Country;
import com.viks.emt_lab.repository.AuthorRepository;
import com.viks.emt_lab.repository.BookRepository;
import com.viks.emt_lab.repository.CountryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataHolder {
    List<Author> authorList1 = null;
    List<Author> authorList2 = null;
    List<Author> authorList3 = null;
    public static List<Book> books = null;
    public static List<Optional<Country>> countries = null;

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CountryRepository countryRepository;


    public DataHolder(AuthorRepository authorRepository, BookRepository bookRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void init(){
        authorList1 = new ArrayList<>();
        authorList2 = new ArrayList<>();
        authorList3 = new ArrayList<>();
        books = new ArrayList<>();
        countries = new ArrayList<Optional<Country>>();

        if (authorRepository.findAll().isEmpty()) {
            Country mk = new Country("Macedonia", "Europe");
            Country rs = new Country("Serbia", "Europe");
            Country de = new Country("Germany", "Europe");
            countries.add(Optional.ofNullable(mk));
            countries.add(Optional.ofNullable(rs));
            countries.add(Optional.ofNullable(de));

            Author a1 = new Author("Author1", "Author1Surname", mk);
            Author a2 = new Author("Author2", "Author2Surname", rs);
            Author a3 = new Author("Author3", "Author3Surname", de);
            List<Author> authorList1 = new ArrayList<>();
            authorList1.add(a1);
            authorList2.add(a2);
            authorList1.add(a3);

            Book b1 = new Book("Book1", Category.HISTORY, authorList1, 3);
            Book b2 = new Book("Book2", Category.CLASSICS, authorList2, 2);
            Book b3 = new Book("Book3", Category.DRAMA, authorList1, 5);
            Book b4 = new Book("Book4", Category.NOVEL, authorList2, 0);
            books.add(b1);
            books.add(b2);
            books.add(b3);
            books.add(b4);

            countryRepository.saveAll(Arrays.asList(mk, rs, de));
            authorRepository.saveAll(authorList1);
            authorRepository.saveAll(authorList2);
            authorRepository.saveAll(authorList3);
            bookRepository.saveAll(books);
        }
    }
}