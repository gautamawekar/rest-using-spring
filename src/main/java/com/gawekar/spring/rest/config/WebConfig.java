package com.gawekar.spring.rest.config;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.gawekar.spring.rest.controller.MyDataFormatController.MyDataConverter;

@Configuration
@EnableWebMvc
@ComponentScan("com.gawekar.spring.util")
public class WebConfig extends WebMvcConfigurerAdapter{
    
    /**
     * Defaults are not added if we override this method. Hence we need to add
     * defaults aswell.
     * 
     * Note for these defaults to work you will need to jaxb & jackson in classpath!!
     * 
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> httpMessageConverters) {
        httpMessageConverters.add(new MappingJackson2HttpMessageConverter());
        httpMessageConverters.add(new Jaxb2RootElementHttpMessageConverter());
        httpMessageConverters.add(new MyDataConverter(new MediaType("text", "mydata")));
    }
}
