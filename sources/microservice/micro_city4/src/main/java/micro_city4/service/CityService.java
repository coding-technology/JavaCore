package micro_city4.service;

import micro_city4.dao.CityDao;

/*
 * Created by 颜群
 */
public class CityService {
    private CityDao cityDao ;

    public CityDao getCityDao() {
        return cityDao;
    }

    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }
}
