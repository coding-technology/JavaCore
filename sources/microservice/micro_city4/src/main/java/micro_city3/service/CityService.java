package micro_city3.service;

import micro_city3.dao.CityDao;

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
