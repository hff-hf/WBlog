package com.cookie.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @auther hff
 * @time 2021/5/2 - 11:06
 **/
@Aspect
@Component
@Slf4j
public class MyAspect {


    @Pointcut("execution(* com.cookie.controller.*.*(..))")
    public void log() {}

    public void doBefore(HttpServletRequest request, JoinPoint joinPoint) {
        String url = request.getRequestURL().toString();
        String addr = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"---->"+joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestArgs requestArgs = new RequestArgs(url, addr, classMethod, args);
        log.info("RequestArgs:{}",requestArgs);
    }

    @After("log()")
    public void doAfter() {

    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void afterReturning(Object result) {
        log.info("result:{}" ,result);
    }

    private class RequestArgs {

        private String url;

        private String ip;

        private String classMethod;

        private Object[] args;

        RequestArgs(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
