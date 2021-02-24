package com.james.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 1.8
 * @ClassName JsonUtils
 * @Description TODO
 * @Author James
 * @date 2021/2/24 11:54
 */
public class JsonUtils {

    public static String getJson(Object object){
        return getJson(object, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getJson(Object object, String dateFormat){
        ObjectMapper mapper = new ObjectMapper();
        //1.如何让他不返回时间戳，所以我们要关闭它的时间戳功能
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //2.时间格式化问题！自定义格式对象
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        //3.让mapper指定时间日期格式为simpleDateFormat
        mapper.setDateFormat(sdf);

        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
