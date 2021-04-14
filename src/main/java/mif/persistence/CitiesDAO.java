package mif.persistence;

import mif.entities.City;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class CitiesDAO {
    @PersistenceContext
    private EntityManager em;

    public void persist(City city){
        this.em.persist(city);
    }
}
