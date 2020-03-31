package micro_httpclient.controller;

import micro_httpclient.service.HttpClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/*
 * Created by 颜群
 */
@RestController
@RequestMapping("http")
public class HttpClientController {

    @Resource
    HttpClientService httpClientService ;

    @GetMapping("doGetHttpClient")
    public String doGetHttpClient() throws Exception{
        System.out.println("doget...");
        String response = httpClientService.doGet("https://www.baidu.com/");
        System.out.println(response);
        return response ;
    }

    @PostMapping("doPostHttpClient")
    public String doPostHttpClient() throws Exception{
        System.out.println("dopost...");
        HashMap<String, String> params = new HashMap<>();
        params.put("name","YANQUN");
        String response = httpClientService.doPost("https://www.baidu.com/", params);
        System.out.println(response);
        return response ;


    }



}
