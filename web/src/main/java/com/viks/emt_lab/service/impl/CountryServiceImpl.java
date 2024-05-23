package com.viks.emt_lab.service.impl;

import com.viks.emt_lab.model.Country;
import com.viks.emt_lab.model.exceptions.InvalidCountryIdException;
import com.viks.emt_lab.repository.CountryRepository;
import com.viks.emt_lab.service.CountryService;
import org.springframework.stereotype.Service;
import com.viks.emt_lab.model.Country;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country findById(Long id) {
        return this.countryRepository.findById(id).orElseThrow(InvalidCountryIdException::new);
    }

    @Override
    public List<Country> getAllCountries() {
        return this.countryRepository.findAll();
    }

    @Override
    public Country addCountry(String name, String continent) {
        Country country = new Country(name, continent);
        return this.countryRepository.save(country);
    }

    @Override
    public void deleteCountry(Country country) {
        this.countryRepository.delete(country);
    }

    @Override
    public Country updateCountry(Long id,String name, String continent) {
        Country countryToUpdate = this.findById(id);
        countryToUpdate.setContinent(continent);
        return this.countryRepository.save(countryToUpdate);
    }
}
