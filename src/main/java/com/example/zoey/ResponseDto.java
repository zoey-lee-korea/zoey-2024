package com.example.zoey;

import lombok.Builder;
import lombok.Data;

@Data
public class ResponseDto {
	private String successOrNot;
	private String statusCode;
	private Object data;

	@Builder
	public ResponseDto(String successOrNot, String statusCode, Object data) {
		this.successOrNot = successOrNot;
		this.statusCode = statusCode;
		this.data = data;
	}
}

/*
-- @Data, @Builder
@Data와 @Builder는 Lombok 라이브러리의 어노테이션입니다.

@Data는 클래스에 대한 getter, setter, equals, hashCode, toString 메소드를 자동으로 생성합니다. 이 어노테이션을 사용하면 이러한 메소드를 직접 작성하지 않아도 됩니다.

@Builder는 빌더 패턴을 자동으로 구현하는 어노테이션입니다.
빌더 패턴을 사용하면 객체를 생성할 때 어떤 파라미터를 어떻게 설정할지 명확하게 표현할 수 있습니다.
또한, 선택적인 파라미터가 많을 때 유용합니다.
 */