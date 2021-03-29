package mif.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.mybatis.dao.CountryMapper;
import mif.mybatis.dao.NeighboursMapper;
import mif.mybatis.model.Country;
import mif.mybatis.model.Neighbours;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class ManageNeighbouringCountriesMyBatis implements Serializable {
    @Inject
    private CountryMapper countryMapper;

    @Inject
    private NeighboursMapper neighboursMapper;

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

    @Transactional
    public String addNewNeighbour(){
        if (newNeighbour == null){
            return "neighbours?faces-redirect=true&countryId=" + this.countryToManage.getId();
        }

        Neighbours neighbours = new Neighbours();
        neighbours.setCountryId(countryToManage.getId());
        neighbours.setNeighboursId(newNeighbour.getId());
        neighboursMapper.insert(neighbours);

        neighbours.setCountryId(newNeighbour.getId());
        neighbours.setNeighboursId(countryToManage.getId());
        neighboursMapper.insert(neighbours);

        return "neighbours?faces-redirect=true&countryId=" + this.countryToManage.getId();
    }

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