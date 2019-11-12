package com.mycode.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @auther kexiangwei
 * @date 2019/7/8
 */
//@Configuration
public class WebMvcConfg implements WebMvcConfigurer {

    /**
     * 跨域设置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*").allowedOrigins("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyHandlerInterceptor()).excludePathPatterns("/getCaptcha.do","/login.do","/logout.do","/error");
    }

    class MyHandlerInterceptor implements HandlerInterceptor {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
            Object user = request.getSession().getAttribute("user");
            if(user==null){
                response.setContentType("text/html;charset=UTF-8");
            	PrintWriter pw = null;
        		try{
                    pw = response.getWriter();
                    JSONObject obj = new JSONObject();
                    obj.put("code", 400);
                    obj.put("msg", "账号未登录");
                    obj.put("data", null);
                    pw.print(obj);
                    pw.flush();
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    pw.close();
                }
            	return false;
            }
            return true;
        }
    }
}
