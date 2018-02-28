package com.zonzie.config.readWriteConfig.dynamicSourceConfig;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zonzie on 2018/2/27.
 */
public class DynamicDataSource extends AbstractRoutingDataSource{
    private final int dataSourceNumber;
    private AtomicInteger count = new AtomicInteger(0);

    public DynamicDataSource(int dataSourceNumber) {
        this.dataSourceNumber = dataSourceNumber;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        //可以做一个简单的负载均衡策略
        String lookupKey = DynamicDataSourceHolder.getDataSource();

        if(StringUtils.isEmpty(lookupKey) || lookupKey.equals(DataSourceType.write.getType()))
            return DataSourceType.write.getType();
        int number = count.getAndAdd(1);
        int i = number % dataSourceNumber;
        System.out.println("------------lookupKey---------"+lookupKey+":"+i);
        return i;
    }
}
