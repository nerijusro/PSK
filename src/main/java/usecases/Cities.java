package usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import entities.City;
import persistence.CitiesDAO;
import persistence.CountriesDAO;

@Model
public class Cities implements Serializable {

    @Inject
    private CitiesDAO citiesDAO;

    @Inject
    private CountriesDAO countriesDAO;

    private City cityToCreate = new City();

    private List<City> allCities;
    @PostConstruct
    public void init(){
        loadCities();
    }

    public void loadCities() {
        this.allCities = citiesDAO.loadAll();
    }

    public List<City> getAllCities(){
        return allCities;
    }

    @Transactional
    public String createCity(){
        this.citiesDAO.persist(cityToCreate);
        return "success";
    }

    public City getCityToCreate() {
        return cityToCreate;
    }

    public void setCityToCreate(City cityToCreate) {
        this.cityToCreate = cityToCreate;
    }
}