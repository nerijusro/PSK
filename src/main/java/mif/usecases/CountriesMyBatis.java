package mif.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.mybatis.dao.CountryMapper;
import mif.mybatis.model.Country;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class CountriesMyBatis {
    @Inject
    private CountryMapper countryMapper;

    @Getter
    private List<Country> allCountries;

    @Getter @Setter
    private Country countryToCreate = new Country();

    @PostConstruct
    public void init() {
        this.loadAllCountries();
    }

    private void loadAllCountries() {
        this.allCountries = countryMapper.selectAll();
    }

    @Transactional
    public String createCountry() {
        countryMapper.insert(countryToCreate);
        return "/myBatis/countries?faces-redirect=true";
    }
}
