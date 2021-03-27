package mif.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.mybatis.dao.CityMapper;
import mif.mybatis.dao.CountryMapper;
import mif.mybatis.model.City;
import mif.mybatis.model.Country;
import mif.utils.CityMapperUtils;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class UpdateCapitalCityMyBatis implements Serializable {

    @Inject
    private CountryMapper countryMapper;

    @Inject
    private CityMapper cityMapper;

    @Getter @Setter
    private Country country;

    @Getter @Setter
    private List<City> cities;

    @Getter @Setter
    private City newCapitalCity = new City();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer countryId = Integer.parseInt(requestParameters.get("countryId"));
        this.country = countryMapper.selectByPrimaryKey(countryId);

        this.cities = CityMapperUtils.loadCities(cityMapper, country);
    }

    @Transactional
    public String updateCapitalCity(){
        if (newCapitalCity == null){
            return "updateCapital?faces-redirect=true&countryId=" + this.country.getId();
        }

        City oldCapital = cityMapper.selectByPrimaryKey(country.getCapitalcityId());
        oldCapital.setIsCapital(false);

        newCapitalCity.setIsCapital(true);

        country.setCapitalcityId(newCapitalCity.getId());

        cityMapper.updateByPrimaryKey(oldCapital);
        cityMapper.updateByPrimaryKey(newCapitalCity);
        countryMapper.updateByPrimaryKey(country);

        return "cities?faces-redirect=true&countryId=" + this.country.getId();
    }
}
