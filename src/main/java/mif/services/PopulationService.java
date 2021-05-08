package mif.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.io.Serializable;

@ApplicationScoped
public class PopulationService implements Serializable {
    public Integer fetchCountrysPopulation(){
        return 1;
    }
}
