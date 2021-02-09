package usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import entities.City;

@Model
public class Cities implements Serializable {

    private List<City> allCities;
    @PostConstruct
    public void init(){
        loadCities();
    }

    public void loadCities() {
        // TODO this is a mock implementation - later we will connect it to real data store
        List<City> cities = new ArrayList<City>();
        cities.add(new City("Jordan"));
        cities.add(new City("Lithuania"));
        this.allCities = cities;
    }

    public List<City> getAllCities(){
        return allCities;
    }
}