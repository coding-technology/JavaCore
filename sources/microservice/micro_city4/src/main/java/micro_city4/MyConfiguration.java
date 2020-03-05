package micro_city4;

import micro_city4.dao.CityDao;
import micro_city4.entity.City;
import micro_city4.service.CityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Created by 颜群
 */
@Configuration
public class MyConfiguration {//配置类

    @Bean
    public CityService cityService(){
        CityService cityService = new CityService();
        CityDao cityDao = new CityDao();
        cityService.setCityDao(  cityDao    );
        return cityService ;
    }
}

