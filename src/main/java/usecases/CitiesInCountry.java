package usecases;

import entities.City;
import entities.Country;
import interceptors.LoggedInvocation;
import lombok.Getter;
import lombok.Setter;
import persistence.CitiesDAO;
import persistence.CountriesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class CitiesInCountry implements Serializable {
    @Inject
    private CitiesDAO citiesDAO;

    @Inject
    private CountriesDAO countriesDAO;

    @Getter @Setter
    private Country country;

    @Getter @Setter
    private City cityToCreate = new City();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer countryId = Integer.parseInt(requestParameters.get("countryId"));
        this.country = countriesDAO.findOne(countryId);
    }

    @Transactional
    @LoggedInvocation
    public String createCity() {
        if(cityToCreate.getName().equals("Vilnius")){
            cityToCreate.setAsCapital();
        }

        cityToCreate.setCountry(this.country);
        citiesDAO.persist(cityToCreate);
        return "cities?faces-redirect=true&countryId=" + this.country.getId();
    }
}
