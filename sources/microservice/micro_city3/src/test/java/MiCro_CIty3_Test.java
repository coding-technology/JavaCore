/*
 * Created by 颜群
 */

import micro_city4.MicroCityApplication;
import micro_city4.dao.CityDao;
import micro_city4.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MicroCityApplication.class)
@RunWith(SpringRunner.class)
public class MiCro_CIty3_Test {

    @Autowired
    ApplicationContext context ; //可以获取IoC容器


    @Test
    public void testIoc(){
        CityService cityService =  (CityService)context.getBean("cityService");
        CityDao cityDao = cityService.getCityDao();

        System.out.println("cityDao:"+cityDao);
    }
}
