package entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "CITY")
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Size(max = 50)
    @Column(name = "COUNTRY")
    private Country country;

    @Size(max = 50)
    @Column(name = "ISCAPITAL")
    private boolean isCapital;

    public City() {
        isCapital = false;
    }

    public City(String name) {
        this.isCapital = false;
        this.name = name;
    }

    public City(String name, Country country, boolean isCapital) {
        this.name = name;
        this.country = country;
        this.isCapital = isCapital;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry(){
        return this.country;
    }

    public void setCountry(Country country, boolean isCapital){
        this.isCapital = isCapital;
        this.country = country;
    }

    public boolean isCapital(){
        return this.isCapital;
    }

    public void setAsCapital(){
        this.isCapital = true;
    }

    public void unsetAsCapital(){
        this.isCapital = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City player = (City) o;
        return Objects.equals(id, player.id) &&
                Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}