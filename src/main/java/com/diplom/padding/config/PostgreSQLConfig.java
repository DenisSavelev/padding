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
@EnableJpaRepositories(basePackages = {"com.diplom.padding.repositories.app"},
        entityManagerFactoryRef = "appEntityManagerFactory",
        transactionManagerRef = "appTransactionManager")
public class PostgreSQLConfig {
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource1")
    public DataSourceProperties postgreSQLProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource postgreSQL(@Qualifier("postgreSQLProperties") DataSourceProperties postgreSQLProperties) {
        return postgreSQLProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean postgreSQLEmFactory(@Qualifier("postgreSQL") DataSource postgreSQL, EntityManagerFactoryBuilder builder) {
        return builder.dataSource(postgreSQL).packages("com.diplom.padding.entity").build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager postgreSQLTransactionManager(EntityManagerFactory postgreSQLEmFactory) {
        return new JpaTransactionManager(postgreSQLEmFactory);
    }
}