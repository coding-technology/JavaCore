package com.yanqun;

import java.util.ArrayList;
import java.util.List;

public class RequestService {
    public static List<RequestInfo> requests ;
    static{
        requests = new ArrayList<>();
        RequestInfo requestZsXd = new RequestInfo(1,"下单");
        RequestInfo requestZsZf = new RequestInfo(1,"支付");
        RequestInfo requestZsTj = new RequestInfo(1,"推荐");

        RequestInfo requestLsXd = new RequestInfo(2,"下单");
        RequestInfo requestLsZf = new RequestInfo(2,"支付");
        RequestInfo requestLsTj = new RequestInfo(2,"推荐");

        RequestInfo requestWwXd = new RequestInfo(3,"下单");
        RequestInfo requestWwZf = new RequestInfo(3,"支付");
        RequestInfo requestWwTj = new RequestInfo(3,"推荐");



        requests.add(requestZsXd) ;
        requests.add(requestZsZf) ;
        requests.add(requestZsTj) ;

        requests.add(requestLsXd) ;
        requests.add(requestLsZf) ;
        requests.add(requestLsTj) ;

        requests.add(requestWwXd) ;
        requests.add(requestWwZf) ;
        requests.add(requestWwTj) ;
    }

    public static List<RequestInfo> getRequests(){
        return requests ;//三个人的9个“消息”
    }
}
