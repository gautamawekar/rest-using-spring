package com.gawekar.spring.rest.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MyData{
    private String name;
    private String surname;
    
    public MyData() {
    }
    
    public MyData(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    
    @XmlAttribute
    public String getName() {
        return name;
    }
    
    @XmlAttribute
    public String getSurname() {
        return surname;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFullName() {
        return name + " " + surname;
    }
}
