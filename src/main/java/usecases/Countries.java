package usecases;

import entities.City;
import entities.Country;
import lombok.Getter;
import lombok.Setter;
import persistence.CountriesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Model
public class Countries {
    @Inject
    private CountriesDAO countriesDAO;

    @Getter @Setter
    private Country countryToCreate = new Country();

    @Getter
    private List<Country> allCountries = new ArrayList<>();

    @PostConstruct
    public void init(){
        loadCountries();
    }

    public void loadCountries() {
        this.allCountries = countriesDAO.loadAll();
    }

    @Transactional
    public String createCountry(){
        City city = new City();
        city.setName("Mazeikiai");
        List<City> cities = new ArrayList<City>();
        cities.add(city);

        countryToCreate.setCities(cities);

        this.countriesDAO.persist(countryToCreate);
        return "success";
    }

    public List<City> getCities(){
        return countriesDAO.getCities();
    }
}
