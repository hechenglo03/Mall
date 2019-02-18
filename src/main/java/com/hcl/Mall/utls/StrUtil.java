package com.hcl.Mall.utls;

import javax.servlet.http.HttpSession;

public class StrUtil {

    public static Object getUserOrSeller(HttpSession session){
        Object result = session.getAttribute(MallConfig.USER_SESSION_KEY);
        if(result != null)
            return result;
        result = session.getAttribute(MallConfig.SELLER_SESSION_KEY);
        return result;
    }
}
