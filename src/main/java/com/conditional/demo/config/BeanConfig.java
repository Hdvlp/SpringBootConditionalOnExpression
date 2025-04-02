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
    //
    // @ConditionalOnProperty is not marked with the @Repeatable annotation
    // and is not designed to be repeatable.
    //
    // Applying the same annotation to a declaration without first declaring it 
    // to be repeatable results in a compile-time error.
    // https://docs.oracle.com/javase/tutorial/java/annotations/repeating.html
    //
    // It is not possible to apply two @ConditionalOnProperty annotations
    // on the same method.
    //
    //
    // The objective is to match _one_ of the two conditions but not both.
    //
    // @ConditionalOnProperty(prefix = "app.config.inside", name = "prop", havingValue = "a")
    // @ConditionalOnProperty(prefix = "app.config.outside", name = "prop", havingValue = "a") //??

    // An alternative in one line:
    // @ConditionalOnExpression("#{('${app.config.inside.prop}' == 'a' && '${app.config.outside.prop}' != 'a') || ('${app.config.inside.prop}' != 'a' && '${app.config.outside.prop}' == 'a')}")


    // Better readability:
    @ConditionalOnExpression(
        "#{" +
            "('${app.config.inside.prop}' == 'a' && " +
            "'${app.config.outside.prop}' != 'a') " +
            "|| " +
            "('${app.config.inside.prop}' != 'a' && " +
            "'${app.config.outside.prop}' == 'a')" +
        "}"
        )
    public SomeBean beanForPropA() {
        System.out.println("Some bean1 is in beanForPropA.");
        return new SomeBean1();
    }

    @Bean

    // @ConditionalOnProperty(prefix = "app.config.inside", name = "prop", havingValue = "b")
    // @ConditionalOnProperty(prefix = "app.config.outside", name = "prop", havingValue = "b") //how to add second?
 
    // An alternative in one line:
    // @ConditionalOnExpression("#{('${app.config.inside.prop}' == 'b' && '${app.config.outside.prop}' != 'b') || ('${app.config.inside.prop}' != 'b' && '${app.config.outside.prop}' == 'b')}")

    // Better readability:
    @ConditionalOnExpression(
        "#{" +
            "('${app.config.inside.prop}' == 'b' && " +
            "'${app.config.outside.prop}' != 'b') " +
            "|| " +
            "('${app.config.inside.prop}' != 'b' && " +
            "'${app.config.outside.prop}' == 'b')" +
        "}"
        )
    public SomeBean beanForPropB() {
        System.out.println("Some bean2 is in beanForPropB.");
        return new SomeBean2();
    }





}
