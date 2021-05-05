package mif.usecases;

import mif.entities.Country;
import lombok.Getter;
import lombok.Setter;
import mif.interceptors.LoggedInvocation;
import mif.persistence.CountriesDAO;
import mif.services.OptLockExceptionLogger;
import mif.services.PopulationService;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@ViewScoped
@Named
@Getter @Setter
public class UpdatePopulation implements Serializable {
    @Inject
    private CountriesDAO countriesDAO;

    @Inject
    private OptLockExceptionLogger exceptionLoggerService;

    @Getter @Setter
    private Country country;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer countryId = Integer.parseInt(requestParameters.get("countryId"));
        this.country = countriesDAO.findOne(countryId);
    }

    @Transactional
    public String updatePopulation() {
        try{
            countriesDAO.merge(this.country);
        } catch (OptimisticLockException e) {
            exceptionLoggerService.saveExceptionInfo(e);
            return "/updatePopulation.xhtml?faces-redirect=true&countryId=" + this.country.getId() + "&error=optimistic-lock-exception";
        }
        return "countryInfo.xhtml?countryId=" + this.country.getId() + "&faces-redirect=true";
    }
}

