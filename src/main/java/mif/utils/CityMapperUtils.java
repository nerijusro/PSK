package mif.utils;

import mif.mybatis.dao.CityMapper;
import mif.mybatis.model.City;
import mif.mybatis.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CityMapperUtils {

    public static List<City> loadCities(CityMapper cityMapper, Country country){
        List<City> allCities = cityMapper.selectAll();
        List<City> filteredCities = new ArrayList<City>();

        for (City city : allCities) {
            if(city.getCountryId() == country.getId()){
                filteredCities.add(city);
            }
        }

        return filteredCities;
    }
}
