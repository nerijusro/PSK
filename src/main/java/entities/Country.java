package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//TODO:
//Add remove city. neighbour.

@Entity
@Table(name = "COUNTRY")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    @ManyToMany
    @JoinTable(name = "NEIGHBOURS")
    private List<Country> neighbours = new ArrayList<>();

    @OneToMany(mappedBy = "country")
    private List<City> cities;

    @OneToOne
    private City capitalCity;

    public Country(){
    }

    public Country(String name, List<City> cities, City capitalCity){
        this.name = name;
        this.cities = cities;

        //TODO:
        //Country must have a capital.
        //Validate if capitalCity is among the cities.
        this.capitalCity = capitalCity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<Country> getNeighbours(){
        return neighbours;
    }

    public void setNeighbours(List<Country> neighbours){
        this.neighbours = neighbours;
    }

    public void addNeighbour(Country neighbour){
        this.neighbours.add(neighbour);
    }

    public List<City> getCities() {
        return this.cities;
    }

    public void setCities(List<City> cities, City capitalCity){
        this.cities = cities;
        this.capitalCity = capitalCity;
    }

    public void addCity(City city){
        this.cities.add(city);
    }

    public City getCapitalCity(){
        return this.capitalCity;
    }

    public void setCapitalCity(City city){
        //TODO:
        //Validate if the new capital is in cities list.
        this.capitalCity = city;
    }
}
