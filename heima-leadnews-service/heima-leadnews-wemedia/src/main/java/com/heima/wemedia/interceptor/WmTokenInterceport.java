package com.heima.wemedia.interceptor;

import com.itheima.model.wemedia.pojos.WmUser;
import com.itheima.utils.tread.WmTreadLocalUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WmTokenInterceport implements HandlerInterceptor {

    /**
     * 得到Header中的用户信息,并存入到当前线程中
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("userId");
        if(userId!=null){
            //存入到线程中
            WmUser wmUser = new WmUser();
            wmUser.setApUserId(Integer.valueOf(userId));
            WmTreadLocalUtil.setUser(wmUser);
        }
        return true ;
    }

    /**
     * 清理线程中的数据
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        WmTreadLocalUtil.clear();
    }
}
