package com.safronova.webproject.controller22.command2222;

import com.safronova.webproject.model.entity2.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AllowedRoles {
    Role[] value() default Role.GUEST;
}
