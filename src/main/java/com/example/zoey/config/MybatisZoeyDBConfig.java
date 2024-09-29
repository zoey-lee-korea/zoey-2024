package com.example.zoey.config;

import javax.sql.DataSource;

import com.example.zoey.ZoeyDBConnMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@MapperScan(value = "com.example.zoey.mapper", annotationClass = ZoeyDBConnMapper.class, sqlSessionFactoryRef = "zoeyDBSqlSessionFactory")
@EnableTransactionManagement
public class MybatisZoeyDBConfig {

	@Bean(name = "zoeyDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource zoeyDBDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "zoeyDBSqlSessionFactory")
	@Primary
	public SqlSessionFactory zoeyDBSqlSessionFactory(
			@Qualifier("zoeyDataSource") DataSource zoeyDBDataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(zoeyDBDataSource);
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:config/mybatis.xml"));
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis-mapper/zoeydb/**/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean(name = "zoeyDBSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate zoeyDBSqlSessionTemplate(SqlSessionFactory zoeyDBSqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(zoeyDBSqlSessionFactory);
	}

}
