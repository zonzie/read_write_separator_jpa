package com.zonzie.config.readWriteConfig.aspect;
import java.lang.annotation.*;

/**
 * 切换数据源
 * 暂时没用
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String dataSource() default "";//数据源
}
