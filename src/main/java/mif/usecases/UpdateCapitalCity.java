package mif.usecases;

import mif.entities.City;
import mif.entities.Country;
import lombok.Getter;
import lombok.Setter;
import mif.persistence.CountriesDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class UpdateCapitalCity implements Serializable {

    @Inject
    private CountriesDAO countriesDAO;

    @Getter @Setter
    private Country country;

    @Getter @Setter
    private City newCapitalCity = new City();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer countryId = Integer.parseInt(requestParameters.get("countryId"));
        this.country = countriesDAO.findOne(countryId);
    }

    @Transactional
    public String updateCapitalCity(){
        if (newCapitalCity == null){
            return "updateCapital?faces-redirect=true&countryId=" + this.country.getId();
        }

        countriesDAO.updateCapital(newCapitalCity);
        return "cities?faces-redirect=true&countryId=" + this.country.getId();
    }
}
