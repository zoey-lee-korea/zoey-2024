package com.example.zoey;

import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ZoeyConstants {
	public static final String DEFAULT_SUCCESS_CODE = "SUCCESS";
	public static final String DEFAULT_SUCCESS_DESC = "Success Response";
	public static final String SUCCESS_FLAG = "Y";

	// zoey
	public enum UserStatus {
		ACT, INACT
	}

	@Getter
	public enum HttpRequestType {
		NEW("POST"),
		DEL("DELETE"),
		MOD("PUT"),
		READ("GET");

		private final String method;

		HttpRequestType(String method) {
			this.method = method;
		}
		// lombok의 @Getter로 대체된 코드
//		public String getMethod() {
//			return this.method;
//		}
	}

}

/*
-- public static final
public, static, final은 Java에서 변수를 선언할 때 사용하는 키워드들입니다. 이들은 각각 다음과 같은 의미를 가집니다:

public: 이 키워드가 붙은 멤버는 어디서든 접근이 가능하다는 것을 의미합니다. 즉, 다른 클래스에서도 이 변수를 참조할 수 있습니다.

static: 이 키워드가 붙은 멤버는 클래스 멤버임을 의미합니다.
즉, 이 변수는 클래스의 인스턴스가 아닌 클래스 자체에 속하며, 클래스의 모든 인스턴스가 이 변수를 공유합니다.
따라서 인스턴스를 생성하지 않고도 이 변수를 사용할 수 있습니다.

final: 이 키워드가 붙은 변수는 한 번 초기화되면 그 값을 변경할 수 없다는 것을 의미합니다. 즉, 이 변수는 상수가 됩니다.

따라서 public static final 키워드를 사용하여 변수를 선언하면, 이 변수는 공개적으로 접근 가능하고 변경 불가능한 클래스 수준의 상수가 됩니다.
이런 특성은 설정 값, 버전 정보, 고정된 메시지 등 프로그램 전체에서 공유하고 변경되지 않아야 하는 값에 사용됩니다


-- @UtilityClass
@UtilityClass는 Lombok 라이브러리에서 제공하는 어노테이션입니다. 이 어노테이션은 클래스에 적용하면 해당 클래스를 유틸리티 클래스로 만들어줍니다.

@UtilityClass를 사용하면 다음과 같은 특징을 가진 클래스가 생성됩니다:

클래스는 final로 선언되어 상속을 방지합니다.
클래스의 모든 메서드는 static으로 선언되어 인스턴스 생성 없이 바로 사용할 수 있습니다.
클래스의 모든 필드는 static으로 선언됩니다.
클래스에는 기본 생성자가 추가되며, 이 생성자는 private로 선언되어 외부에서 인스턴스를 생성하는 것을 방지합니다.

샘플 코드에서 MyUtilityClass는 @UtilityClass 어노테이션으로 인해 유틸리티 클래스로 만들어졌습니다.
따라서 MyUtilityClass.doSomething()과 같이 메서드를 바로 호출할 수 있습니다.

 */
