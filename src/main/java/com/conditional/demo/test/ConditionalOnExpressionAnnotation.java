package com.conditional.demo.test;

import java.lang.reflect.Method;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import com.conditional.demo.config.BeanConfig;

public class ConditionalOnExpressionAnnotation {

    public static boolean test(String methodName, String expectedValue){
        try {
            Class<?> clazz = BeanConfig.class;
            Method method = clazz.getMethod(methodName);

            if (method.isAnnotationPresent(ConditionalOnExpression.class)) {
                ConditionalOnExpression s = method.getAnnotation(ConditionalOnExpression.class);
                return s.value().equals(expectedValue);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void customTest(){
        String methodName = "beanForPropA";
        System.out.println(
            String.format("Is the same expected value for annotating %s? ", methodName) + 
            test( methodName, 
                 "#{('${app.config.inside.prop}' == 'a' && '${app.config.outside.prop}' != 'a') || ('${app.config.inside.prop}' != 'a' && '${app.config.outside.prop}' == 'a')}")
        );

        methodName = "beanForPropB";
        System.out.println(
            String.format("Is the same expected value for annotating %s? ", methodName) + 
            test( methodName, 
                 "#{('${app.config.inside.prop}' == 'b' && '${app.config.outside.prop}' != 'b') || ('${app.config.inside.prop}' != 'b' && '${app.config.outside.prop}' == 'b')}")
        );
    }
    
}
