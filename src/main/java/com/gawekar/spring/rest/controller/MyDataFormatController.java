package com.gawekar.spring.rest.controller;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gawekar.spring.rest.config.WebConfig;
import com.gawekar.spring.rest.model.MyData;

/**
 * 
 * Content Negotiation in Spring:
 * 
 * This sample shows how to add your own MessageConverters on top of defaults.
 * 
 * What are @RequestBody and @ResponseBody for?
 * They are annotations of the spring mvc framework.they are used to implement 
 * smart object serialization and deserialization.
 * 
 * If you annotate a method with @ResponseBody, spring will try to convert its 
 * return value and write it to the http response automatically. 
 * 
 * If you annotate a methods parameter with @RequestBody, spring will try to 
 * convert the content of the incoming request body to your parameter object on the fly.
 * 
 * start web application & give following urls (GET requests) to see different data conversions
 * (add header 'Accept' or use urls)
 * Fetch xml with extension '.xml' 
 * http://localhost:8080/rest/mydata.xml
 * or add header Accept : application/xml for request
 * http://localhost:8080/rest/mydata
 * 
 * Fetch json with extension '.json' 
 * http://localhost:8080/rest/mydata.json
 * or add header Accept : application/json
 * 
 * Fetch custom format add header Accept : text/mydata for GET request
 * http://localhost:8080/rest/mydata
 * 
 * @author gawekar
 *
 * @see WebConfig
 */

@Controller
public class MyDataFormatController {
    
    private MyData myData;
    
    @RequestMapping(value="/mydata", method = RequestMethod.GET,produces={"application/xml", "application/json","text/mydata"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MyData getMyData() {
        if (this.myData == null) {
            //throw new RuntimeException("Need to set the data before getting it");
            this.myData = new MyData("Default Name", "Default Surname");
        }
        return this.myData;
    }
    
    @RequestMapping(value="/mydata",method = RequestMethod.POST,consumes= {"application/xml", "application/json","text/mydata"})
    @ResponseStatus(HttpStatus.CREATED)
    public void setData(@RequestBody MyData myData) {
        this.myData = myData; 
    }
    
    
    
    public static class MyDataConverter extends AbstractHttpMessageConverter<MyData> {
        
        public MyDataConverter(MediaType supportedMediaType) {
            super(supportedMediaType);
        }
        
        @Override
        protected MyData readInternal(Class<? extends MyData> clazz,
                HttpInputMessage httpInputMessage) throws IOException,
                HttpMessageNotReadableException {
            String body = IOUtils.toString(httpInputMessage.getBody());
            String data[] = body.split(",");
            return new MyData(data[0], data[1]);
        }
        
        @Override
        protected boolean supports(Class<?> aClass) {
            return MyData.class.equals(aClass);
        }
        
        @Override
        protected void writeInternal(MyData myData, HttpOutputMessage httpOutputMessage)
                throws IOException, HttpMessageNotWritableException {
            IOUtils.write(myData.getFullName(), httpOutputMessage.getBody());
        }
    }
}
