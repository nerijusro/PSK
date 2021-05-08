package mif.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
@Specializes
public class ImprovedPopulationService extends PopulationService {

    @Override
    public Integer fetchCountrysPopulation(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        Integer fetchedPopulation = new Random().nextInt(100000000);
        return fetchedPopulation;
    }
}
