package com.gawekar.spring.mvc.bean;

import org.springframework.stereotype.Component;

@Component
public class LoadedFromApplicationContext {
    public LoadedFromApplicationContext() {
        System.out.println(">>>>>>>>>>>LoadedFromApplicationContext loaded");
    }
}
