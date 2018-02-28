package com.zonzie.config.readWriteConfig.dynamicSourceConfig;

/**
 * Created by zonzie on 2018/2/27.
 */
public class DynamicDataSourceHolder {
    //使用ThreadLocal把数据源与当前线程绑定
    private static final ThreadLocal<String> DATASOURCES = new ThreadLocal<>();

    public static void setDataSource(String dataSourceName) {
        DATASOURCES.set(dataSourceName);
    }

    public static String getDataSource() {
        return DATASOURCES.get();
    }

    public static void clearDataSource() {
        DATASOURCES.remove();
    }
}
