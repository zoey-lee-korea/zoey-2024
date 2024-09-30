package com.example.zoey.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEncryptableProperties
public class JasyptZoeyConfig {
	@Value("${jasypt.encryptor.password}")
	private String PASSWORD;

	@Bean("jasyptStringEncryptor")
	public StringEncryptor jasyptStringEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(PASSWORD);
		config.setPoolSize("1");
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setStringOutputType("base64");
		config.setKeyObtentionIterations("1000");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		encryptor.setConfig(config);
		return encryptor;
	}
}

/*
-- @Value("${jasypt.encryptor.password}")
@Value는 Spring Framework에서 제공하는 어노테이션으로, 프로퍼티 값들을 필드에 주입하는 데 사용됩니다.

"${jasypt.encryptor.password}"는 Spring Expression Language (SpEL) 표현식입니다.
이 표현식은 jasypt.encryptor.password라는 이름의 프로퍼티 값을 참조합니다.

따라서 @Value("${jasypt.encryptor.password}")는 jasypt.encryptor.password 프로퍼티의 값을 해당 필드에 주입하라는 의미입니다.
application.properties -> jasypt.encryptor.password=MySecretKey
application.yml ->
jasypt:
  encryptor:
	password: MySecretKey

Spring Boot 애플리케이션에서는 JVM 시스템 프로퍼티를 사용하여 외부에서 설정 값을 주입할 수 있습니다.
java -Djasypt.encryptor.password=MySecretKey -jar myapp.jar
이 방식은 설정 값을 소스 코드나 설정 파일에 직접 작성하지 않고, 실행 시점에 동적으로 설정 값을 주입할 수 있어 유용합니다.
특히 민감한 정보를 안전하게 관리하거나, 다양한 환경에서 애플리케이션을 실행할 때 유용하게 사용할 수 있습니다.


-- Jaspyt
Jasypt는 Java에서 사용할 수 있는 간단하고 강력한 암호화 라이브러리입니다


-- @EnableEncryptableProperties
@EnableEncryptableProperties는 Jasypt Spring Boot 라이브러리에서 제공하는 어노테이션입니다.
이 어노테이션은 Spring Boot 애플리케이션의 프로퍼티 값들을 암호화할 수 있도록 합니다.

이 어노테이션을 사용하면, 프로퍼티 파일(application.properties 또는 application.yml)에 저장된 민감한 정보(예: 데이터베이스 비밀번호)를 암호화하여 보안을 강화할 수 있습니다.

@EnableEncryptableProperties를 사용하려면, 먼저 Jasypt Spring Boot 라이브러리를 프로젝트에 추가해야 합니다.
그런 다음, Spring Boot 애플리케이션의 메인 클래스나 설정 클래스에 @EnableEncryptableProperties를 추가합니다.


-- typo 체크 옵션 해제 (IntelliJ)
File > Settings (또는 Ctrl+Alt+S)를 선택합니다.
설정 창에서 Editor > Inspections을 선택합니다.
검사 설정 목록에서 General > Proofreading을 찾습니다.
Proofreading 옆의 체크박스를 해제하여 오타 검사를 비활성화합니다.
OK 버튼을 클릭하여 설정을 저장하고 창을 닫습니다.

 */