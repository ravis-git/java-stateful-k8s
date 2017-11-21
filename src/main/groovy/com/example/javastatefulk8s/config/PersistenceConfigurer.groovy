package com.example.javastatefulk8s.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.OpenJpaVendorAdapter

import javax.inject.Inject
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

/**
 * Created by ravipalakodeti on 11/21/17.
 */
@Configuration
@EnableJpaRepositories('com.example.javastatefulk8s.model')
class PersistenceConfigurer {

    @Value('${emf.packagesToScan}')
    private String packagesToScan

    @Inject
    private DataSource dataSource

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean()
        emf.setDataSource(dataSource)
        emf.setPackagesToScan(packagesToScan)
        emf.setJpaVendorAdapter(new OpenJpaVendorAdapter())
        emf.afterPropertiesSet()
        return emf.getObject()
    }

}
