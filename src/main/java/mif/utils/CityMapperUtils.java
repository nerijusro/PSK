package mif.utils;

import mif.mybatis.dao.CityMapper;
import mif.mybatis.model.City;
import mif.mybatis.model.Country;

import java.util.List;

public class CityMapperUtils {

    public static List<City> loadCities(CityMapper cityMapper, Country country){
        List<City> cities = cityMapper.selectAll();

        for (City city : cities) {
            if(city.getCountryId() != country.getId()){
                cities.remove(city);
            }
        }

        return cities;
    }
}
