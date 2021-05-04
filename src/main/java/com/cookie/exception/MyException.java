package com.cookie.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther hff
 * @time 2021/5/1 - 20:31
 **/
@ControllerAdvice
@Slf4j
public class MyException {


    @ExceptionHandler(Exception.class)
    public ModelAndView exception(HttpServletRequest request , Exception e) throws Exception {
      log.error("httpRequest:{} , Exception:{}",request.getRequestURL(),e);
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        ModelAndView view = new ModelAndView();
        view.addObject("exception",e);
        view.addObject("HttpServletRequest",request.getRequestURL());
        view.setViewName("error/error");
        return view;
    }
}
