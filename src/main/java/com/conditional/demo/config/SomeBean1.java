package com.conditional.demo.config;

public class SomeBean1 implements SomeBean{
    public SomeBean1(){ 
        System.out.println("Some bean1 is constructed."); 
    }

    public void doSomething() {
        System.out.println("Some bean1 is active.");
    }

}