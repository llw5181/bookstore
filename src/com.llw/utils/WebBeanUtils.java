package com.llw.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebBeanUtils {

    /**
     * 封装第三方jar包，BeanUtils.populate()方法，将bean属性名对应Map中key的value注入到bean对象属性中
     * request的请求参数可以用request.getParameterMap()将请求参数转化为map
     *
     * @param value
     * @param bean
     * @return 返回将map中的value注入属性值后的bean对象
     */
    public static <T> T copyParamToBean(Map value, T bean) {
        try {
            /*
            第三方jia包BeanUtils工具类方法，将bean属性名对应Map中key的value注入到bean对象属性中
             */
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int parseInt(String strInt, int defaultValue) {

        try {
            return Integer.parseInt(strInt.trim());
        }catch (Exception e) {
//            打印错误信息
//            e.printStackTrace();
            System.out.println("src/com/llw/utils/WebBeanUtils.java 中的parseInt()出错误啦！！ 转化字符串为数字这里噢！");
        }
        return defaultValue;
    }
}