package com.conditional.demo.config;


public class SomeBean2 implements SomeBean{
    public SomeBean2(){ 
        System.out.println("Some bean2 is constructed."); 
    }

    public void doSomething() {
        System.out.println("Some bean2 is active.");
    }
}

