package entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "City.findAll", query = "select a from City as a")
})
@Table(name = "CITY")
@Getter @Setter
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name="COUNTRY_ID")
    private Country country;

    @Column(name = "IS_CAPITAL")
    private boolean isCapital;

    public City() {
        isCapital = false;
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