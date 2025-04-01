package com.conditional.demo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    // SpEL expressions can be used with XML or annotation-based configuration metadata 
    // for defining BeanDefinitions. In both cases the syntax to define the expression is 
    // of the form #{ <expression string> }.
    // https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/expressions.html

    @Bean
    @ConditionalOnExpression("#{('${app.config.inside.prop}' == 'a' && '${app.config.outside.prop}' != 'a') || ('${app.config.inside.prop}' != 'a' && '${app.config.outside.prop}' == 'a')}")
    public CustomBean customInsideOrOutsideNotBoth() {
        System.out.println("app.config.inside _or_ app.config.outside _not_ both");
        return new CustomBean();
    }

    @Bean
    // It is not possible to use two @ConditionalOnProperty annotations:
    // @ConditionalOnProperty(prefix = "app.config.inside", name = "prop", havingValue = "a")
    // @ConditionalOnProperty(prefix = "app.config.outside", name = "prop", havingValue = "a") //??
    @ConditionalOnExpression("#{('${app.config.inside.prop}' == 'a' && '${app.config.outside.prop}' != 'a') || ('${app.config.inside.prop}' != 'a' && '${app.config.outside.prop}' == 'a')}")
    public SomeBean beanForPropA() {
        System.out.println("Some bean1 is in beanForPropA.");
        return new SomeBean1();
    }

    @Bean
    // It is not possible to use two @ConditionalOnProperty annotations:
    // @ConditionalOnProperty(prefix = "app.config.inside", name = "prop", havingValue = "b")
    // @ConditionalOnProperty(prefix = "app.config.outside", name = "prop", havingValue = "b") //how to add second?
    @ConditionalOnExpression("#{('${app.config.inside.prop}' == 'b' && '${app.config.outside.prop}' != 'b') || ('${app.config.inside.prop}' != 'b' && '${app.config.outside.prop}' == 'b')}")
    public SomeBean beanForPropB() {
        System.out.println("Some bean2 is in beanForPropB.");
        return new SomeBean2();
    }


}
