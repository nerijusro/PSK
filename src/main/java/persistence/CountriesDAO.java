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

    private City getCapitalCity(){ return em.createNamedQuery("Country.getCapitalCity", City.class).getSingleResult(); }

    private void resetCapitalCity(){
        City oldCapital = this.getCapitalCity();
        oldCapital.unsetAsCapital();

        em.merge(oldCapital);
    }

    public City updateCapital(City newCapital){
        this.resetCapitalCity();

        newCapital.setAsCapital();
        return em.merge(newCapital);
    }

    public void persist(Country city){
        this.em.persist(city);
    }

    public Country findOne(Integer id) {
        return em.find(Country.class, id);
    }
}
