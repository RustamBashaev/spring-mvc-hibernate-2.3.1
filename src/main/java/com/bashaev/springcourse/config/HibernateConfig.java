package com.bashaev.springcourse.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.annotation.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Рустам
 */

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:db.properties")
@ComponentScan(value  ="com.bashaev.springcourse")
@EnableJpaRepositories("com.bashaev.springcourse.repository")
public class HibernateConfig {

    @Autowired
    private Environment environment;

//    public HibernateConfig(Environment environment) {
//        this.environment = environment;
//    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(environment.getRequiredProperty("db.url"));
        ds.setDriverClassName(environment.getRequiredProperty("db.driver"));
        ds.setUsername(environment.getRequiredProperty("db.username"));
        ds.setPassword(environment.getRequiredProperty("db.password"));

        ds.setInitialSize(Integer.valueOf(environment.getRequiredProperty("db.initialSize")));
        ds.setMinIdle(Integer.valueOf(environment.getRequiredProperty("db.minIdle")));
        ds.setMaxIdle(Integer.valueOf(environment.getRequiredProperty("db.maxIdle")));
        ds.setTimeBetweenEvictionRunsMillis(Long.valueOf(environment.getRequiredProperty("db.timeBetweenEvictionRunsMillis")));
        ds.setMinEvictableIdleTimeMillis(Long.valueOf(environment.getRequiredProperty("db.minEvictableIdleTimeMillis")));
        ds.setTestOnBorrow(Boolean.valueOf(environment.getRequiredProperty("db.testOnBorrow")));
        ds.setValidationQuery(environment.getRequiredProperty("db.validationQuery"));

        return ds;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

        return manager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em =
                new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(environment.getRequiredProperty("db.entity.package"));        
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(getHibernateProperties());

        return em;
    }

    private Properties getHibernateProperties() {
        try {
            Properties properties = new Properties();
            InputStream is = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
            properties.load(is);

            return properties;
        } catch (IOException e) {
            throw new IllegalArgumentException("can't find file");
        }
    }


//    private HibernateJpaVendorAdapter vendorAdapter() {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setShowSql(true);
//        return vendorAdapter;
//    }
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(environment.getProperty("db.driver"));
//        dataSource.setUrl(environment.getProperty("db.url"));
//        dataSource.setUsername(environment.getProperty("db.username"));
//        dataSource.setPassword(environment.getProperty("db.password"));
//        return dataSource;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
//        Properties props = new Properties();
//        props.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
//        props.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
//        props.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
//
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setJpaVendorAdapter(vendorAdapter()); //??
//        factoryBean.setDataSource(dataSource());
//        factoryBean.setJpaProperties(props);
//        factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
//        factoryBean.setPackagesToScan("com.bashaev.springcourse.entity");
//        return factoryBean;
//    }
//
//    @Bean
//    public JpaTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(getEntityManagerFactory().getObject());
//        return transactionManager;
//    }
}
