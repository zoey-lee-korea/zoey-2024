package com.example.zoey.config;

import javax.sql.DataSource;

import com.example.zoey.ZoeyDBConnMapper;
import org.apache.ibatis.session.ExecutorType;
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

	@Bean(name = "batchZoeyDBSqlSessionTemplate")
	public SqlSessionTemplate batchZoeyDBSqlSessionTemplate(SqlSessionFactory zoeyDBSqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(zoeyDBSqlSessionFactory, ExecutorType.BATCH);
	}
}

/*
-- @EnableTransactionManagement
@EnableTransactionManagement는 Spring Framework에서 선언적 트랜잭션 관리를 활성화하는 어노테이션입니다.

이 어노테이션을 설정 파일이나 설정 클래스에 추가하면, Spring은 @Transactional 어노테이션이 붙은 메소드에 대해 트랜잭션 경계를 자동으로 관리합니다.
즉, @Transactional 어노테이션이 붙은 메소드가 호출될 때
트랜잭션을 시작하고, 메소드가 정상적으로 종료되면 트랜잭션을 커밋하며, 메소드 실행 중 예외가 발생하면 트랜잭션을 롤백합니다.

이를 통해 개발자는 트랜잭션 관리에 대한 부담 없이 비즈니스 로직에 집중할 수 있습니다.


-- @MapperScan
@MapperScan은 MyBatis에서 제공하는 어노테이션으로, 특정 패키지를 스캔하여 MyBatis의 매퍼로 등록하는 역할을 합니다.

value = "com.example.zoey.mapper"는 스캔할 패키지의 경로를 지정합니다. 이 경우, "com.example.zoey.mapper" 패키지 내의 모든 클래스를 스캔합니다.

annotationClass = ZoeyDBConnMapper.class는 스캔 대상이 되는 매퍼 인터페이스에 붙어 있는 어노테이션을 지정합니다.
이 경우, ZoeyDBConnMapper 어노테이션이 붙은 인터페이스만 매퍼로 등록됩니다.

sqlSessionFactoryRef = "zoeyDBSqlSessionFactory"는 매퍼가 사용할 SqlSessionFactory 빈의 이름을 지정합니다.
이 경우, "zoeyDBSqlSessionFactory"라는 이름의 SqlSessionFactory 빈을 사용합니다.


-- SqlSessionFactory
SqlSessionFactory는 MyBatis의 핵심 컴포넌트 중 하나로, SqlSession 인스턴스를 생성하는 역할을 합니다.
SqlSession은 SQL 명령을 실행하고 결과를 반환하는 메소드를 제공하므로, 실제 데이터베이스 작업을 수행하는 데 필요합니다.

SqlSessionFactory는 설정 파일이나 프로그래밍 방식으로 MyBatis 구성 정보를 받아들입니다.
이 정보에는 데이터베이스 연결 정보, 트랜잭션 설정, 매퍼 설정 등이 포함될 수 있습니다.

SqlSessionFactory는 일반적으로 애플리케이션에서 한 번만 생성되고, 이후에는 재사용됩니다.
SqlSession은 요청 당 하나가 생성되므로, SqlSessionFactory는 애플리케이션의 모든 SQL 작업에 대해 SqlSession 인스턴스를 생성하는 공장 역할을 합니다.

sqlSessionFactory.openSession()은 새 SqlSession 인스턴스를 생성하고,
session.close()는 SqlSession을 닫아 리소스를 해제합니다.


-- @Primary
@Primary는 Spring Framework에서 제공하는 어노테이션으로, 같은 타입의 빈이 여러 개 있을 때 우선적으로 주입해야 할 빈을 지정하는 데 사용됩니다.

예를 들어, 같은 인터페이스를 구현하는 두 개의 빈이 있고, 하나의 빈에 @Primary를 붙였다면,
Spring은 @Autowired나 @Inject를 사용하여 빈을 주입할 때 @Primary가 붙은 빈을 우선적으로 선택합니다.

예를 들어, 데이터베이스 연결을 위한 두 개의 DataSource 빈이 있을 때를 생각해봅시다. 하나는 메인 데이터베이스에 연결하고, 다른 하나는 백업 데이터베이스에 연결합니다.
대부분의 경우에는 메인 데이터베이스에 연결해야 하므로, 메인 DataSource 빈에 @Primary를 붙여서 기본적으로 이 빈이 주입되도록 설정할 수 있습니다.

@Bean
@Primary
public DataSource mainDataSource() {
	// 메인 데이터베이스에 연결하는 DataSource 생성
}
@Bean
public DataSource backupDataSource() {
	// 백업 데이터베이스에 연결하는 DataSource 생성
}
이렇게 설정하면, DataSource 타입의 빈을 주입받는 모든 곳에 메인 DataSource가 주입됩니다.

그러나 특정한 경우에 백업 DataSource를 사용해야 한다면, @Qualifier 어노테이션을 사용하여 명시적으로 백업 DataSource를 주입받도록 설정할 수 있습니다.
@Autowired
@Qualifier("backupDataSource")
private DataSource dataSource;


-- @Qualifier
@Qualifier는 Spring Framework에서 제공하는 어노테이션으로, 같은 타입의 빈이 여러 개 있을 때, 특정 빈을 주입하고자 할 때 사용합니다.

예를 들어, 두 개의 DataSource 빈이 있고, 하나는 메인 데이터베이스에 연결하고, 다른 하나는 백업 데이터베이스에 연결한다고 가정해봅시다.
이 경우, @Autowired나 @Inject를 사용하여 DataSource를 주입받는 곳에서 @Qualifier를 사용하여 어떤 DataSource 빈을 주입받을지 지정할 수 있습니다.

*/
