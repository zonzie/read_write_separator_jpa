package com.zonzie.config.readWriteConfig;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by zonzie on 2018/2/27.
 */
@Configuration
@EnableJpaRepositories(value = "com.zonzie.*.repository",entityManagerFactoryRef = "entityManagerFactory",transactionManagerRef = "transactionManager")
public class InitiateDataSource {

    @Resource
    JpaProperties jpaProperties;

    @Resource(name = "routingDataSource")
    private DataSource dataSource;

    /**
     * 我们通过LocalContainerEntityManagerFactoryBean来获取EntityManagerFactory实例
     * @return
     */
    @Bean(name = "entityManagerFactoryBean")
//    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .properties(jpaProperties.getProperties())
                .packages("com.zonzie.*.repository.entity") //设置实体类所在位置
//                .persistenceUnit("writePersistenceUnit")
                .build();
        //.getObject();//不要在这里直接获取EntityManagerFactory
    }

    /**
     * EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
     * 总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session,
     * mybatis的sqlSession.
     * @param builder
     * @return
     */
    @Bean(name = "entityManagerFactory")
    @Primary
    public EntityManagerFactory entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return this.entityManagerFactoryBean(builder).getObject();
    }

    /**
     * 配置事物管理器
     * @return
     */
    @Bean(name = "transactionManager")
//    @Primary
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        MyJpaTransactionManager myJpaTransactionManager = new MyJpaTransactionManager();
        myJpaTransactionManager.setEntityManagerFactory(entityManagerFactory(builder));
        return myJpaTransactionManager;
    }

}
