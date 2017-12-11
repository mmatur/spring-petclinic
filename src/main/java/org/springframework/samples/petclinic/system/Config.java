package org.springframework.samples.petclinic.system;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Configuration
public class Config extends WebMvcConfigurerAdapter {

    private String petclinicCss;

    @PostConstruct
    private void postContruct() {
        List<String> lstCss = Lists.newArrayList();
        lstCss.add("/resources/css/petclinic-green.css");
        lstCss.add("/resources/css/petclinic-blue.css");
        lstCss.add("/resources/css/petclinic-red.css");
        lstCss.add("/resources/css/petclinic-orange.css");
        lstCss.add("/resources/css/petclinic-purple.css");
        lstCss.add("/resources/css/petclinic-pink.css");
        lstCss.add("/resources/css/petclinic-yellow.css");
        lstCss.add("/resources/css/petclinic-grey.css");

        petclinicCss = lstCss.get(RandomUtils.nextInt(0, lstCss.size()));
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BaseInterceptor());
    }

    public class BaseInterceptor extends HandlerInterceptorAdapter {

        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            String controllerName = "";
            String actionName = "";

            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                controllerName = handlerMethod.getBeanType().getSimpleName();
                actionName = handlerMethod.getMethod().getName();
            }
            if (modelAndView != null) {
                modelAndView.addObject("petcinicCss", petclinicCss);
                modelAndView.addObject("controllerName", controllerName);
                modelAndView.addObject("actionName", actionName);
            }
        }

    }
}
