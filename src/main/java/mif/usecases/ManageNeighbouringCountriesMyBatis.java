package mif.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.mybatis.dao.CountryMapper;
import mif.mybatis.model.Country;

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
public class ManageNeighbouringCountriesMyBatis implements Serializable {
    @Inject
    private CountryMapper countryMapper;

    @Getter @Setter
    private Country countryToManage;

    @Getter @Setter
    private Country newNeighbour;

    @Getter
    private List<Country> countries = new ArrayList<>();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer countryId = Integer.parseInt(requestParameters.get("countryId"));
        this.countryToManage = countryMapper.selectByPrimaryKey(countryId);
        loadValidCountries();
    }

//    @Transactional
//    public String addNewNeighbour(){
//        if (newNeighbour == null){
//            return "neighbours?faces-redirect=true&countryId=" + this.countryToManage.getId();
//        }
//
//        List<Country> neighbouringCountries = countryToManage.getNeighbours();
//        neighbouringCountries.add(newNeighbour);
//        countryToManage.setNeighbours(neighbouringCountries);
//
//        neighbouringCountries = newNeighbour.getNeighbours();
//        neighbouringCountries.add(countryToManage);
//        newNeighbour.setNeighbours(neighbouringCountries);
//
//        countriesDAO.merge(countryToManage);
//        countriesDAO.merge(newNeighbour);
//        return "neighbours?faces-redirect=true&countryId=" + this.countryToManage.getId();
//    }

    public void loadValidCountries() {
        this.countries = countryMapper.selectAll();
        List<Integer> neighboursIds = new ArrayList<Integer>();
        neighboursIds.add(countryToManage.getId());

        List<Country> neighbours = countryToManage.getNeighbours();
        for (Country neighbour : neighbours){
            neighboursIds.add(neighbour.getId());
        }

        removeCountry(neighboursIds);
    }

    private void removeCountry(List<Integer> ids){
        while(!ids.isEmpty()){
            for (Country country: this.countries) {
                if(ids.contains(country.getId())){
                    countries.remove(country);
                    ids.remove(country.getId());
                    break;
                }
            }
        }
    }
}