package com.llw.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {

    /**
     * 返回从客户端请求Cookie中查找是否有有指定name的cookie
     * @param cookieName 需要查找指定Cookie的name
     * @param cookies    request.getCookies 从请求头获取的全部cookie
     * @return 如果没有指定名称的cookie则返回null，如果有则返回
     */
    public static Cookie findCookie(String cookieName, Cookie[] cookies) {
        if (cookieName == null || cookies == null || cookies.length == 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }
}
