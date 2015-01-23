package com.my.biz.sm.commons.token;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class TokenInterceptor extends HandlerInterceptorAdapter
{
    private final static Logger log = LoggerFactory.getLogger(TokenInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        if (handler instanceof HandlerMethod)
        {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Token annotation = method.getAnnotation(Token.class);
            if (annotation != null)
            {
                boolean needSaveSession = annotation.save();
                if (needSaveSession)
                {
                    request.getSession(false).setAttribute("token", UUID.randomUUID().toString());
                }
                boolean needRemoveSession = annotation.remove();
                if (needRemoveSession)
                {
                    if (isRepeatSubmit(request))
                    {
                       // request.setAttribute("msg", "您还没有登录或登录已超时，请重新登录，然后再刷新本功能！"); 
                        //response.sendRedirect(request.getContextPath()+"/index");  
                        response.setHeader("Content-type", "text/html;charset=UTF-8");  
                        response.getWriter().print("<script>alert('请不要重复提交表单！');history.go(-2);</script>");
                        response.addHeader("Cache-Control", "no-cache");
                        response.addHeader("Expires", new Date().toString());
                        return false;
                    }
                    request.getSession(false).removeAttribute("token");
                }
            }
            return true;
        }
        else
        {
            return super.preHandle(request, response, handler);
        }
    }

    private boolean isRepeatSubmit(HttpServletRequest request)
    {
        String serverToken = (String) request.getSession(false).getAttribute("token");
        if (serverToken == null)
        {
            return true;
        }
        String clinetToken = request.getParameter("token");
        if (clinetToken == null)
        {
            return true;
        }
        if (!serverToken.equals(clinetToken))
        {
            return true;
        }
        return false;
    }
}
