package com.viks.emt_lab.web.rest;

import com.viks.emt_lab.model.Author;
import com.viks.emt_lab.model.Book;
import com.viks.emt_lab.model.dto.CountryDTO;
import com.viks.emt_lab.model.Country;
import com.viks.emt_lab.service.AuthorService;
import com.viks.emt_lab.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin (origins = "http://localhost:3000")
@RequestMapping("/")
public class CountryController {

    private final CountryService countryService;
    private final AuthorService authorService;

    @Autowired
    public CountryController(CountryService countryService, AuthorService authorService) {
        this.countryService = countryService;
        this.authorService = authorService;
    }

    @PostMapping("/country/add")
    public ResponseEntity<Void> addCountry(@RequestBody CountryDTO countryDTO) {
        if(countryDTO == null) {
            return ResponseEntity.notFound().build();
        }
        this.countryService.addCountry(countryDTO.getName(), countryDTO.getContinent());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/countries")
    public List<Country> getCountries() {
        return this.countryService.getAllCountries();
    }

    @GetMapping("/country/{countryId}")
    public Country getCountry(@PathVariable Long countryId){
        if (countryId == null){
            ResponseEntity.notFound().build();
        }
        return this.countryService.findById(countryId);
    }
    @DeleteMapping("/country/{countryId}/delete")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long countryId){
        if (countryId == null){
            ResponseEntity.notFound().build();
        }
        Country countryToDelete = this.countryService.findById(countryId);
        List<Author> authors =  this.authorService.getAllAuthors();
        for (int i=0; i<authors.size(); i++){
            if (authors.get(i).getCountry().equals(countryToDelete)) {
                authors.get(i).setCountry(null);
            }
        }
        this.countryService.deleteCountry(countryToDelete);
        return ResponseEntity.ok().build();
    }
}