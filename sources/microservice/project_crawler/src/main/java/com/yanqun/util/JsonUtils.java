package com.yanqun.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yanqun.entity.Course;

/*
 * Created by 颜群
 */
public class JsonUtils {
    static ObjectMapper objectMapper = new ObjectMapper();

    //json对象 - 对象(一行记录)

    //json对象(字符串形式) -> 对象
    public static<T> T json2Object(String json ,Class<T> valueType) throws Exception{
        return objectMapper.readValue(json, valueType);
    }


    // 对象 -> json对象(字符串形式)
    public static String  object2json(Object value) throws Exception{
        return objectMapper.writeValueAsString( value ) ;
    }

    public static void main(String[] args)throws Exception {
        //演示json对象(字符串形式) -> 对象
         String json = "{\"name\":\"java\",\"num\":\"30\",\"imgPath\":\"sxxx\"  }" ;
        Course course = JsonUtils.json2Object(json, Course.class);
        System.out.println(course);

    }

}
