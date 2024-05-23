package com.viks.emt_lab.model;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "books")
public class Book{

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToMany (cascade = CascadeType.REMOVE)
    private List<Author> author;
    @Column(name = "available copies")
    private Integer availableCopies;

    public Book(){}
    public Book(String name, Category category, List<Author> author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
