package com.example.zoey;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ZoeyDBConnMapper {
	String value() default "";
}

/*
-- @Target
@Target({ ElementType.TYPE }): @Target 어노테이션은 다른 어노테이션이 적용될 수 있는 Java 요소를 지정합니다.
@Target 어노테이션은 어노테이션 선언에 사용되며, 어노테이션을 적용할 수 있는 위치를 제한하는 역할을 합니다.
TYPE: 클래스, 인터페이스, 열거형
FIELD: 필드, 열거형의 상수
METHOD: 메서드
PARAMETER: 매개변수
CONSTRUCTOR: 생성자
LOCAL_VARIABLE: 지역 변수
ANNOTATION_TYPE: 어노테이션 타입
PACKAGE: 패키지

@Target(ElementType.METHOD)
public @interface MyAnnotation {
    // ...
}
예를들어, 어노테이션을 메서드에만 적용하려면 다음과 같이 @Target 어노테이션을 사용할 수 있습니다
이렇게 선언한 MyAnnotation은 메서드에만 적용할 수 있습니다. 클래스, 필드, 매개변수 등 다른 Java 요소에는 적용할 수 없습니다.


-- @Retention, @Documented, @Component
@Retention(RetentionPolicy.RUNTIME): 이 어노테이션은 어노테이션이 유지되는 기간을 지정합니다.
RetentionPolicy.RUNTIME은 런타임에도 이 어노테이션이 유지되어야 함을 의미합니다. 따라서 런타임에 이 어노테이션 정보를 읽을 수 있습니다.

@Documented: Java에서 @Documented는 메타-어노테이션입니다. 메타-어노테이션은 다른 어노테이션에 적용되는 어노테이션을 말합니다.
@Documented 어노테이션은 어노테이션이 사용된 요소의 Javadoc에도 해당 어노테이션 정보가 포함되어야 함을 지정합니다.
즉, @Documented가 붙은 어노테이션은 Javadoc 도구를 사용하여 문서를 생성할 때 해당 어노테이션 정보가 문서에 포함됩니다.

@Component: 이 어노테이션은 클래스를 Spring의 컴포넌트로 선언합니다.
이 어노테이션을 사용하면 Spring이 이 클래스를 관리하며, 이 클래스의 인스턴스를 필요로 하는 다른 클래스에 주입할 수 있습니다.


-- @interface
@interface는 Java에서 어노테이션 타입을 선언할 때 사용하는 키워드입니다.
@ 기호는 이 인터페이스가 어노테이션 타입임을 나타냅니다.
이렇게 선언된 어노테이션 타입은 다른 클래스, 메서드, 필드 등에 어노테이션으로 적용할 수 있습니다.

어노테이션은 코드의 동작에 직접적인 영향을 미치지 않지만,
어노테이션을 처리하는 도구나 프레임워크에게 추가 정보를 제공하여 코드의 동작을 변경하거나, 코드에 대한 정보를 제공하는 데 사용됩니다.

public @interface MyAnnotation {
    String value() default "";
}

@MyAnnotation("Hello, world!")
public class MyClass {
    // ...
}
이 코드는 MyClass에 MyAnnotation을 적용하고, value 요소에 "Hello, world!"라는 값을 설정합니다.


-- Javadoc
Javadoc은 Java 코드에서 주석을 추출하여 HTML 형식의 API 문서를 생성하는 도구입니다.
이 도구는 Java의 표준 일부로 제공되며, 클래스, 메서드, 변수 등의 선언 바로 위에 작성된 주석을 분석하여 문서를 생성합니다.

Javadoc 주석은 일반 주석과는 다르게 /**... 형식을 사용합니다. 이 주석 안에는 HTML 태그를 사용할 수 있으며, 특별한 어노테이션을 사용하여 문서의 섹션을 나눌 수 있습니다.

예를 들어, 다음과 같이 Javadoc 주석을 작성할 수 있습니다:
이 코드에서 Javadoc 도구는 MyClass 클래스와 myMethod 메서드에 대한 문서를 생성합니다.
이 문서에는 클래스와 메서드의 설명, 메서드의 매개변수와 반환값에 대한 설명, 작성자 정보 등이 포함됩니다.
/**
 * This is a description of the class.
 *
 * @author Zoey Lee
 * /
public class MyClass {
    /**
     * This is a description of the method.
     *
     * @param param A description of the parameter.
     * @return A description of the return value.
     * /
    public int myMethod(int param) {
        // ...
    }
}

*/