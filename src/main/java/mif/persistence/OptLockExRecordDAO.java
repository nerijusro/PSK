package mif.persistence;

import mif.entities.OptLockExRecord;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class OptLockExRecordDAO {
    @PersistenceContext
    private EntityManager em;

    public void persist(OptLockExRecord record){
        this.em.persist(record);
    }
}
