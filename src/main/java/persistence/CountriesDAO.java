package persistence;

import entities.City;
import entities.Country;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class CountriesDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Country> loadAll() {
        return em.createNamedQuery("Country.findAll", Country.class).getResultList();
    }

    public List<City> getCities(){ return em.createNamedQuery("Country.getCities", City.class).getResultList(); }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Country city){
        this.em.persist(city);
    }
}
