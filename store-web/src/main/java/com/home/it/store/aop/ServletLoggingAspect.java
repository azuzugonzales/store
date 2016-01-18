package com.home.it.store.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

import static com.home.it.jdbc.utils.LoggingUtil.getCurrentClassName;

@Aspect
public class ServletLoggingAspect {
    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Before("execution(* com.home.it.store.servlets..do*(..))")
    public void logDoGetRequest(JoinPoint joinPoint) {
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        Enumeration<String> parameterNames = request.getParameterNames();
        StringBuilder sb = new StringBuilder("?");
        while (parameterNames.hasMoreElements()) {
            String current = parameterNames.nextElement();
            sb.append(current + "=" + request.getParameter(current));
            sb.append("&");
        }
        String log = request.getRequestURL() + sb.toString();
        logger.debug("Request received :" + (log.substring(0, log.length() - 1)));
    }
}
