package com.zonzie.config.readWriteConfig.aspect;

/**
 * Created by zonzie on 2018/2/27.
 */

import com.zonzie.config.readWriteConfig.dynamicSourceConfig.DataSourceType;
import com.zonzie.config.readWriteConfig.dynamicSourceConfig.DynamicDataSourceHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * aop 配置动态切换数据源
 */
@Aspect
@Component
public class DynamicDataSourceAspect {
    /*
    @Around("execution(public * com.zonzie.*.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method targetMethod = methodSignature.getMethod();
        if (targetMethod.isAnnotationPresent(TargetDataSource.class)) {
            String targetDataSource = targetMethod.getAnnotation(TargetDataSource.class).dataSource();
            System.out.println("----------数据源是:" + targetDataSource + "------");
            DynamicDataSourceHolder.setDataSource(targetDataSource);
        }
        Object result = pjp.proceed();//执行方法
        return result;
    }
*/
    @Around("execution(* com.zonzie.*.service.impl.*.select*(..)) || execution(* com.zonzie.*.service.impl.*.find*(..))")
    public Object setReadDataSourceType(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("------------------readAspect------------------");
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Class declaringType = signature.getDeclaringType();
        boolean annotationPresent = declaringType.isAnnotationPresent(Transactional.class);
        if(!annotationPresent) {
//            String dataSource = DynamicDataSourceHolder.getDataSource();
//            if(StringUtils.isEmpty(dataSource))
            DynamicDataSourceHolder.setDataSource(DataSourceType.read.getType());
        }
        return pjp.proceed();
    }

    @Around("execution(* com.zonzie.*.service.impl.*.insert*(..)) || execution(* com.zonzie.*.service.impl.*.update*(..))")
    public Object setWriteDataSourceType(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("------------------writeAspect------------------");
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Class declaringType = signature.getDeclaringType();
        boolean annotationPresent = declaringType.isAnnotationPresent(Transactional.class);
        if(!annotationPresent) {
//            String dataSource = DynamicDataSourceHolder.getDataSource();
//            if(StringUtils.isEmpty(dataSource))
            DynamicDataSourceHolder.setDataSource(DataSourceType.write.getType());
        }
        return pjp.proceed();
    }
}
