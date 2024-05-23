package com.viks.emt_lab.service;
import com.viks.emt_lab.model.Country;

import java.util.List;

public interface CountryService {
    Country findById(Long id);
    List<Country> getAllCountries();
    Country addCountry(String name, String continent);
    void deleteCountry(Country country);
    Country updateCountry(Long id,String name, String continent);
}
