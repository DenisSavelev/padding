package com.diplom.padding.config;

import javax.sql.DataSource;
import javax.persistence.EntityManagerFactory;

import org.springframework.orm.jpa.*;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.diplom.padding.repositories.moodle"},
        entityManagerFactoryRef = "moodleEntityManagerFactory",
        transactionManagerRef = "moodleTransactionManager")
public class MySQLConfig {
    @Bean
    @ConfigurationProperties("spring.datasource2")
    public DataSourceProperties mySQLProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource mySQL(@Qualifier("postgreSQLProperties") DataSourceProperties mySQLProperties) {
        return mySQLProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean mySQLEmFactory(@Qualifier("postgreSQL") DataSource mySQL, EntityManagerFactoryBuilder builder) {
        return builder.dataSource(mySQL).packages("com.diplom.padding.entity").build();
    }

    @Bean
    public PlatformTransactionManager mySQLTransactionManager(EntityManagerFactory mySQLEmFactory) {
        return new JpaTransactionManager(mySQLEmFactory);
    }
}