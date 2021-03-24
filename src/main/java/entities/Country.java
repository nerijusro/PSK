package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Country.findAll", query = "select a from Country as a"),
        @NamedQuery(name = "Country.getCities", query = "select a from City a where a.name = :name"),
        @NamedQuery(name = "Country.getCapitalCity", query = "select a from City a where a.isCapital = true and a.country.id = :id")
})
@Table(name = "COUNTRY")
@Getter @Setter
public class Country {

    public Country(){
        
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany
    @JoinTable(name = "NEIGHBOURS")
    private List<Country> neighbours = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "country")
    private List<City> cities = new ArrayList<>();

    @OneToOne
    private City capitalCity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country team = (Country) o;
        return Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
