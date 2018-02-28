package com.zonzie.config.readWriteConfig.dynamicSourceConfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zonzie on 2018/2/27.
 */
@Configuration
public class DataSourceRouting {

    @Value("${spring.datasource.readsize}")
    private String dataSourceSize;

    /**
     * 注入AbstractRoutingDataSource
     * @param readDruidDataSources
     * @param writeDruidDataSource
     * @return
     * @throws Exception
     */
    @Bean("routingDataSource")
    public AbstractRoutingDataSource routingDataSource(
            @Qualifier("readDataSources") List<DataSource> readDruidDataSources,
            @Qualifier("writeDataSource") DataSource writeDruidDataSource
    ) throws Exception {
        int size = Integer.parseInt(dataSourceSize);
        DynamicDataSource dataSource = new DynamicDataSource(size);
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.write.getType(), writeDruidDataSource);

        for(int i = 0; i< size; i++) {
            targetDataSources.put(i, readDruidDataSources.get(i));
        }
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(writeDruidDataSource);
        return dataSource;
    }
}
