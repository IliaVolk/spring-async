package com.springapp.config;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    @Bean
    public BasicDataSource createDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/lucky_pets");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setInitialSize(1);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager txManager(BasicDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    /*<!--data source DBCP (pools of connections) begins-->
    <bean id="dataSource"
    class="org.apache.commons.dbcp2.BasicDataSource">
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/springbl"/>
    <property name="username" value="root"/>
    <property name="password" value="root"/>
    <property name="initialSize" value="1"/>
    </bean>
    <!--data source DBCP ends-->*/
}
