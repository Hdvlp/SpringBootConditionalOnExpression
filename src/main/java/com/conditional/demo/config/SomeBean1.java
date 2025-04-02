package com.conditional.demo.config;

public class SomeBean1 implements SomeBean{
    public SomeBean1(){ 
        System.out.println("Some bean1 is constructed."); 
    }

    public void configSomething() {
        System.out.println("Some bean1 is active.");
    }

}