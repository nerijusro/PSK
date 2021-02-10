package usecases;

import entities.City;
import entities.Country;
import persistence.CountriesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Model
public class Countries implements Serializable {
    @Inject
    private CountriesDAO countriesDAO;

    private Country countryToCreate = new Country();

    private List<Country> allCountries = new ArrayList<>();

    @PostConstruct
    public void init(){
        loadCountries();
    }

    public void loadCountries() {
        this.allCountries = countriesDAO.loadAll();
    }

    public List<Country> getAllCountries(){
        return allCountries;
    }

    @Transactional
    public String createCountry(){
        this.countriesDAO.persist(countryToCreate);
        return "success";
    }

    @Transactional
    public List<City> getCities(){
        return countriesDAO.getCities();
    }

    public Country getCountryToCreate() {
        return countryToCreate;
    }

    public void setCountryToCreate(Country countryToCreate) {
        this.countryToCreate = countryToCreate;
    }
}
