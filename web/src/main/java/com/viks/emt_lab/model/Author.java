package com.viks.emt_lab.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Optional;

@Data
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @ManyToOne (cascade = CascadeType.REMOVE)
    private Country country;

    public Author(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public Author() {}
}
