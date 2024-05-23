package com.viks.emt_lab.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.viks.emt_lab.model.Author;
import com.viks.emt_lab.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class BookDTO {
    private String name;
    private Category category;
    @JsonProperty("authorId")
    private Long author;
    private Integer availableCopies;

    public BookDTO(String name, Category category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}