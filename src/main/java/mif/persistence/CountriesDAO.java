package mif.persistence;

import mif.entities.City;
import mif.entities.Country;

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

    private City getCapitalCity(Country country){
        List<City> capitalCity = em.createNamedQuery("Country.getCapitalCity", City.class).setParameter("id", country.getId()).getResultList();
        if(capitalCity.isEmpty()){
            return null;
        }

        return capitalCity.get(0);
    }

    private void resetCapitalCity(Country country){
        City oldCapital = country.getCapitalCity();

        oldCapital.unsetAsCapital();
        em.merge(oldCapital);
    }

    private boolean hasCapitalCity(Country country){
        City oldCapital = this.getCapitalCity(country);
        if(oldCapital != null) {
            return true;
        }

        return false;
    }

    public City updateCapital(City newCapital){
        Country country = findOne(newCapital.getCountry().getId());
        boolean isCapitalCitySet = hasCapitalCity(country);

        if(isCapitalCitySet){
            resetCapitalCity(country);
        }

        newCapital.setAsCapital();
        country.setCapitalCity(newCapital);
        em.merge(country);
        return em.merge(newCapital);
    }

    public void persist(Country country){
        this.em.persist(country);
    }

    public void merge(Country country) { this.em.merge(country); }

    public Country findOne(Integer id) {
        return em.find(Country.class, id);
    }
}
