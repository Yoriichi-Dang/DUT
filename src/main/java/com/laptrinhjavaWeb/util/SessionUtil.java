package com.laptrinhjavaWeb.util;
import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
    private static SessionUtil sessionUtil=null;
    public static SessionUtil getInstance(){
        if(sessionUtil==null){
            sessionUtil=new SessionUtil();
        }
        return sessionUtil;
    }
    public void putValue(HttpServletRequest request, String key, Object value){
        request.getSession().setAttribute(key,value);
        //add session with key is user and value is user name
    }
    public Object getValue(HttpServletRequest request,String key){
        //get value of key
        return request.getSession().getAttribute(key);
    }
    public void removeValue(HttpServletRequest request,String key){
        //remove session after logout
        request.getSession().removeAttribute(key);
    }
}
