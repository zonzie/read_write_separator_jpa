package com.zonzie.config.readWriteConfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zonzie on 2018/2/27.
 */
@Configuration
public class LoadDataSource {
    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "writeDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource writeDataSource() {
        System.out.println("-----------------write-------------------");
//        return DataSourceBuilder.create().type(dataSourceType).build();
        return new DruidDataSource();
    }

    @Bean(name = "readDataSource1")
    @ConfigurationProperties(prefix = "spring.read1")
    public DataSource read1DataSource() {
        System.out.println("------------------read1---------------------");
//        return DataSourceBuilder.create().type(dataSourceType).build();
        return new DruidDataSource();
    }

    @Bean(name = "readDataSource2")
    @ConfigurationProperties(prefix = "spring.read2")
    public DataSource read2DataSource() {
        System.out.println("------------------read2---------------------");
//        return DataSourceBuilder.create().type(dataSourceType).build();
        return new DruidDataSource();
    }

    @Bean("readDataSources")
    public List<DataSource> readDataSources(){
        List<DataSource> dataSources=new ArrayList<>();
        dataSources.add(read1DataSource());
        dataSources.add(read2DataSource());
        return dataSources;
    }
}
