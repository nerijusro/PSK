package usecases;

import entities.Country;
import lombok.Getter;
import lombok.Setter;
import persistence.CountriesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Model
public class ManageNeighbouringCountries implements Serializable {
    @Getter @Setter
    private Country countryToManage;

    @Getter @Setter
    private Country newNeighbour;

    @Inject
    private CountriesDAO countriesDAO;

    @Getter
    private List<Country> countries = new ArrayList<>();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer countryId = Integer.parseInt(requestParameters.get("countryId"));
        this.countryToManage = countriesDAO.findOne(countryId);
        loadValidCountries();
    }

    @Transactional
    public String addNewNeighbour(){
        if (newNeighbour == null){
            return "neighbours?faces-redirect=true&countryId=" + this.countryToManage.getId();
        }

        List<Country> neighbouringCountries = countryToManage.getNeighbours();
        neighbouringCountries.add(newNeighbour);
        countryToManage.setNeighbours(neighbouringCountries);

        neighbouringCountries = newNeighbour.getNeighbours();
        neighbouringCountries.add(countryToManage);
        newNeighbour.setNeighbours(neighbouringCountries);

        countriesDAO.merge(countryToManage);
        countriesDAO.merge(newNeighbour);
        return "neighbours?faces-redirect=true&countryId=" + this.countryToManage.getId();
    }

    public void loadValidCountries() {
        this.countries = countriesDAO.loadAll();
        this.countries.remove(countryToManage);

        List<Country> neighbours = countryToManage.getNeighbours();

        for (Country neighbour : neighbours){
            this.countries.remove(neighbour);
        }
    }
}
