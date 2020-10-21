package com.onpassive.onet.config;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "groupEntityManagerFactory", 
		transactionManagerRef = "groupTransactionManager", 
		basePackages = {
				"com.onpassive.onet.repository"
		}
)
public class GroupDBConfig {

	@Bean(name = "groupDataSource")
	@ConfigurationProperties(prefix = "spring.group.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "groupEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean bookEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("groupDataSource") DataSource dataSource) {
		
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		
		return builder.dataSource(dataSource)
					  .properties(properties)
					  .packages("com.onpassive.onet.model")
					  .persistenceUnit("GroupModel")
					  .build();
	}

	@Bean(name = "groupTransactionManager")
	public PlatformTransactionManager bookTransactionManager(@Qualifier("groupEntityManagerFactory") EntityManagerFactory groupEntityManagerFactory) {
		return new JpaTransactionManager(groupEntityManagerFactory);
	}
}
