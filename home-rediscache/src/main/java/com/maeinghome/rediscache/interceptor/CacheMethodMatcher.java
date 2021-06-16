package com.maeinghome.rediscache.interceptor;

import org.springframework.aop.MethodMatcher;

import java.lang.reflect.Method;

public class CacheMethodMatcher implements MethodMatcher {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return false;
    }

    @Override
    public boolean isRuntime() {
        return false;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        return false;
    }
}
