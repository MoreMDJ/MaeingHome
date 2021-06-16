package com.maeinghome.rediscache.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheUsedAspect {

    @Around("execution(* com.maeinghome.rediscache.service.ICacheService.selectOne(..))")
    public Object selectOneAround(ProceedingJoinPoint point) throws Throwable {
        Object proceed = point.proceed();
        System.out.println("selectOneAround = " + proceed);
        return proceed;
    }

    @Around("execution(* org.springframework.data.redis.cache.RedisCache.lookup(..))")
    public Object cacheUsingAround(ProceedingJoinPoint point) throws Throwable {
        Object proceed = point.proceed();
        System.out.println("cacheUsingAround = " + proceed);
        return proceed;
    }

    @Before("execution(* org.springframework.cache.Cache.get(*))")
    public void cacheUsingBefore(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        Object target = joinPoint.getTarget();
        Object aThis = joinPoint.getThis();
        JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
        String longString = joinPoint.toLongString();
        String shortString = joinPoint.toShortString();
        System.out.println("cacheUsingBefore = " + joinPoint);
    }

}
