package com.hcl.Mall.utls;

import javax.servlet.http.HttpSession;

public class StrUtil {

    public static Object getBuyerOrSeller(HttpSession session){
        Object result = session.getAttribute(MallConfig.USER_SESSION_KEY);
        return result;
    }
}
