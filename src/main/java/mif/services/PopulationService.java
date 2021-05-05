package mif.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class PopulationService implements Serializable {
    public Integer fetchCountrysPopulation(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        Integer fetchedPopulation = new Random().nextInt(100000000);
        return fetchedPopulation;
    }
}
