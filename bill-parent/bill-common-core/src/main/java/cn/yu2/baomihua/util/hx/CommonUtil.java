package cn.yu2.baomihua.util.hx;


import com.alibaba.fastjson.JSON;

/**
 * Created by LuckyH on 2017-03-07.
 */
public class CommonUtil {

    public static String objectToGson(Object object){
        return   JSON.toJSONString(object);
    }
    }
