package com.example.senstive.senstive.ano;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ehyzil
 * @Description
 * @create 2024-01-2024/1/3-20:44
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SensitiveField {

    String bind() default "";
}
