package mif.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Country.findAll", query = "select a from Country as a"),
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

    @Column(name = "NAME")
    private String name;

    @Column(name = "POPULATION")
    private Integer population;

    @OneToOne
    private City capitalCity;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "country")
    private List<City> cities = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "NEIGHBOURS")
    private List<Country> neighbours = new ArrayList<>();

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

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
