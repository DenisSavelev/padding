package com.diplom.padding.config;

import org.springframework.orm.jpa.*;
import org.springframework.context.annotation.*;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.diplom.padding.repositories.moodle"},
        entityManagerFactoryRef = "mySQLEntityManager",
        transactionManagerRef = "mySQLTransactionManager")
public class MySQLConfiguration {
    @Bean
    public LocalContainerEntityManagerFactoryBean mySQLEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(mySQLDataSource());
        em.setPackagesToScan("com.diplom.padding.entity.moodle");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public DataSource mySQLDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/moodle?serverTimezone=UTC&useSSL=true&verifyServerCertificate=false");
        dataSource.setUsername("root");
        dataSource.setPassword("qwerty123");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager mySQLTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mySQLEntityManager().getObject());
        return transactionManager;
    }
}