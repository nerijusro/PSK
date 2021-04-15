package mif.services;

import mif.entities.OptLockExRecord;
import mif.persistence.OptLockExRecordDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;

@ApplicationScoped
public class OptLockExceptionLogger implements Serializable {

    @Inject
    private OptLockExRecordDAO exceptionDAO;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void saveExceptionInfo(OptimisticLockException e){
        OptLockExRecord exceptionData = new OptLockExRecord(e.getMessage());
        exceptionDAO.persist(exceptionData);
    }
}
