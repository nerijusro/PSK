package mif.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.mybatis.dao.CityMapper;
import mif.mybatis.dao.CountryMapper;
import mif.mybatis.model.City;
import mif.mybatis.model.Country;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class CitiesInCountryMyBatis {
    @Inject
    private CityMapper cityMapper;

    @Inject
    private CountryMapper countryMapper;

    @Getter @Setter
    private Country country;

    @Getter @Setter
    private List<City> cities;

    @Getter @Setter
    private City cityToCreate = new City();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer countryId = Integer.parseInt(requestParameters.get("countryId"));
        this.country = countryMapper.selectByPrimaryKey(countryId);

        this.cities = loadCities();
    }

    private List<City> loadCities(){
        List<City> cities = cityMapper.selectAll();

        for (City city : cities) {
            if(city.getCountryId() != country.getId()){
                cities.remove(city);
            }
        }

        return cities;
    }

    @Transactional
    public String createCity() {
        cityToCreate.setCountryId(this.country.getId());
        cityToCreate.setIsCapital(false);
        int cityId = cityMapper.insert(cityToCreate);

        if(country.getCapitalcityId() == null){
            cityToCreate.setIsCapital(true);
            country.setCapitalcityId(cityId);

            cityMapper.updateByPrimaryKey(cityToCreate);
            countryMapper.updateByPrimaryKey(country);
        }

        return "cities?faces-redirect=true&countryId=" + this.country.getId();
    }

//    private void resetCapitalCity(mif.entities.Country country){
//        mif.entities.City oldCapital = country.getCapitalCity();
//
//        oldCapital.unsetAsCapital();
//        em.merge(oldCapital);
//    }
//
//    private boolean hasCapitalCity(mif.entities.Country country){
//        mif.entities.City oldCapital = this.getCapitalCity(country);
//        if(oldCapital != null) {
//            return true;
//        }
//
//        return false;
//    }
//
//    public mif.entities.City updateCapital(mif.entities.City newCapital){
//        mif.entities.Country country = findOne(newCapital.getCountry().getId());
//        boolean isCapitalCitySet = hasCapitalCity(country);
//
//        if(isCapitalCitySet){
//            resetCapitalCity(country);
//        }
//
//        newCapital.setAsCapital();
//        country.setCapitalCity(newCapital);
//        em.merge(country);
//        return em.merge(newCapital);
//    }
}
