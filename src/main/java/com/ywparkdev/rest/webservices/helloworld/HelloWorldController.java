package com.ywparkdev.rest.webservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello_world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello_world_bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World Bean");
    }

    @GetMapping(path = "/hello_world/path-variable/{name}")
    public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    // Option 1
//    @GetMapping(path="/hello-world-internationalized")
//    public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", required = false) Locale locale) {
//        return messageSource.getMessage("good.morning.message", null, locale);
//    }

    // Option 2
    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized() {
        return messageSource.getMessage("good.morning.message", null,
                LocaleContextHolder.getLocale());
    }
}
