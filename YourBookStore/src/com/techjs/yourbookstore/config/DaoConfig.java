package com.techjs.yourbookstore.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.techjs.yourbookstore.dao")
@PropertySource("classpath:database.properties")
@PropertySource("classpath:hibernate.properties")
public class DaoConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
	}
	
	private Properties hibernateProperties() {
		Properties props = new Properties();
		props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		props.put("hibernate.use_sql_comments", env.getProperty("hibernate.use_sql_comments"));
		props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		props.put("hibernate.max_fetch_depth", env.getProperty("hibernate.max_fetch_depth"));
		props.put("hibernate.jdbc.batch_size", env.getProperty("hibernate.jdbc.batch_size"));
		props.put("hibernate.jdbc.fetch_size", env.getProperty("hibernate.jdbc.fetch_size"));
		return props;
	}
	
	@Bean
	public SessionFactory sessionFactory() throws IOException {
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setDataSource(dataSource());
		bean.setPackagesToScan("com.techjs.yourbookstore.model");
		bean.setHibernateProperties(hibernateProperties());
		bean.afterPropertiesSet();
		return bean.getObject();
	}
	

	@Bean
	public PlatformTransactionManager transactionManager() throws IOException {
		return new HibernateTransactionManager(sessionFactory());
	}
}
