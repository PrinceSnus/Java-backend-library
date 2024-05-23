package com.viks.emt_lab.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.viks.emt_lab.model.Country;
import lombok.Data;

@Data
public class AuthorDTO {
    private String name;
    private String surname;
    @JsonProperty("countryId")
    private Long country;


    public AuthorDTO(String name, String surname, Long country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
