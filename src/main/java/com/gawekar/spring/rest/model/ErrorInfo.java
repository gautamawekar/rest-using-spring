package com.gawekar.spring.rest.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorInfo {  
    @XmlAttribute
    private String url;
    @XmlAttribute
    private String message;
    
    public ErrorInfo() {
        
    }
      
    public ErrorInfo(String url, String message) {  
        this.url = url;  
        this.message = message;  
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }  
}
