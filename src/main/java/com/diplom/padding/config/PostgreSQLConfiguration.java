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
        basePackages = {"com.diplom.padding.repositories.app"},
        entityManagerFactoryRef = "postgreEntityManager",
        transactionManagerRef = "postgreTransactionManager")
public class PostgreSQLConfiguration {

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean postgreEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(postgreDataSource());
        em.setPackagesToScan("com.diplom.padding.entity.app");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Primary
    @Bean
    public DataSource postgreDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://167.172.172.240:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("GolBol1999a");
        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager postgreTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(postgreEntityManager().getObject());
        return transactionManager;
    }
}