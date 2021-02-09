package persistence;

import entities.City;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class CitiesDAO {
    @PersistenceContext
    private EntityManager em;

    public List<City> loadAll() {
        return em.createNamedQuery("City.findAll", City.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(City city){
        this.em.persist(city);
    }
}
