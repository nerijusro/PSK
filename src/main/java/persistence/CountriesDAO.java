package persistence;

import entities.Country;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class CountriesDAO {
    @PersistenceContext
    private EntityManager em;

    public void persist(Country city){
        this.em.persist(city);
    }
}
