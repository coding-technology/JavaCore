package com.yanqun.micro_city2.remote.city;

import com.yanqun.entity.Message;
import com.yanqun.entity.StatusCode;
import com.yanqun.micro_city2.entity.City;
import org.springframework.stereotype.Component;

/*
 * Created by 颜群
 */
@Component
public class CityClientImpl implements  CityClient {

    @Override
    public Message queryCityBiId(Integer id) {
        return new Message(false, StatusCode.ERROR, "请求失败，触发了本地熔断");
    }

    @Override
    public Message addCity(City city) {
        return  new Message(false, StatusCode.ERROR, "请求失败，触发了本地熔断");
    }
}
