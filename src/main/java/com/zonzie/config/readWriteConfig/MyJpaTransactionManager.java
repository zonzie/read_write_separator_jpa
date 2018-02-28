package com.zonzie.config.readWriteConfig;

import com.zonzie.config.readWriteConfig.dynamicSourceConfig.DataSourceType;
import com.zonzie.config.readWriteConfig.dynamicSourceConfig.DynamicDataSource;
import com.zonzie.config.readWriteConfig.dynamicSourceConfig.DynamicDataSourceHolder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

/**
 * Created by zonzie on 2018/2/28.
 */
public class MyJpaTransactionManager extends JpaTransactionManager {

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        boolean readOnly = definition.isReadOnly();
        if(!readOnly) {
            DynamicDataSourceHolder.setDataSource(DataSourceType.write.getType());
        }

        System.out.println("--transaction:---------"+readOnly+"-------------");
        super.doBegin(transaction, definition);
    }

    @Override
    protected void doCommit(DefaultTransactionStatus status) {
        String dataSource = DynamicDataSourceHolder.getDataSource();
        System.out.println("---dataSource------------"+dataSource);
        super.doCommit(status);
    }
}
