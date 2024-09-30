package com.example.zoey.utils;

import com.example.zoey.ResponseDto;
import com.example.zoey.ZoeyConstants;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class ResponseUtils {

	// Common
	@JsonSerialize
	public static class EmptyJsonResponse {
	}

	public static ResponseEntity<ResponseDto> createSuccessResponse(Object data, HttpStatus httpStatus) {
		return createSuccessResponse(ZoeyConstants.DEFAULT_SUCCESS_CODE, ZoeyConstants.DEFAULT_SUCCESS_DESC, data, httpStatus);
	}

	public static ResponseEntity<ResponseDto> createSuccessResponse(String statusCode, String description, Object data, HttpStatus httpStatus) {
		return new ResponseEntity<>(createSuccessResponseData(statusCode, description, data), httpStatus);
	}

	private static ResponseDto createSuccessResponseData(String statusCode, String description, Object data) {
		if (data == null)
			data = new EmptyJsonResponse();

		return ResponseDto.builder().successOrNot(ZoeyConstants.SUCCESS_FLAG).statusCode(statusCode).data(data)
				.build();
	}

	// GET
	public static ResponseEntity<ResponseDto> createGetSuccessResponse(Object data) {
		return createSuccessResponse(data, HttpStatus.OK);
	}
}

/*
-- @UtilityClass
@UtilityClass와 @JsonSerialize는 각각 Lombok 라이브러리와 Jackson 라이브러리의 어노테이션입니다.

@UtilityClass는 Lombok에서 제공하는 어노테이션으로, 이 어노테이션은 클래스가 유틸리티 클래스임을 나타냅니다.
이 어노테이션을 사용하면 Lombok은 모든 메소드를 static으로 만들고, 클래스의 인스턴스를 생성하는 것을 방지하기 위해 private 생성자를 추가합니다.
이렇게 하면 유틸리티 클래스를 안전하게 사용할 수 있습니다.

1) 모든 메소드를 static으로 만든다는 것은, 이 메소드들이 클래스 인스턴스에 종속되지 않고 클래스 자체에 속하게 된다는 것을 의미합니다.
즉, 클래스의 인스턴스를 생성하지 않고도 이 메소드들을 사용할 수 있습니다. 이는 메모리 사용량을 줄이고 코드의 가독성을 높이는 효과가 있습니다.

2) 클래스의 인스턴스를 생성하는 것을 방지하는 이유는, 유틸리티 클래스는 상태를 가지지 않는 메소드들만을 모아놓은 클래스이므로 인스턴스를 생성할 필요가 없기 때문입니다.
인스턴스를 생성하면 메모리를 불필요하게 사용하게 됩니다.

3) private 생성자를 추가하면, 이 클래스는 외부에서 인스턴스를 생성할 수 없게 됩니다.
하지만 모든 메소드가 static이므로, 메소드를 사용하려면 클래스 이름을 통해 직접 호출하면 됩니다.
예를 들어, UtilityClass.method()와 같은 형태로 사용할 수 있습니다. 따라서 private 생성자가 추가되더라도 메소드를 다른 곳에서 사용하는 데는 문제가 없습니다.


-- @JsonSerialize
@JsonSerialize는 Jackson 라이브러리에서 제공하는 어노테이션으로, 이 어노테이션은 클래스나 메소드, 필드가 JSON으로 직렬화되는 방식을 지정합니다.
이 어노테이션을 사용하면 직렬화 프로세스를 세밀하게 제어할 수 있습니다.
이 경우, EmptyJsonResponse 클래스는 특별한 직렬화 방식이 지정되지 않았으므로, Jackson은 기본 직렬화 방식을 사용하여 이 클래스의 인스턴스를 JSON으로 변환합니다.

"JSON으로 직렬화한다"는 말은, 객체나 데이터를 JSON 형식의 문자열로 변환한다는 의미입니다.
이 과정을 통해 데이터를 파일에 저장하거나 네트워크를 통해 전송하는 등의 작업을 수행할 수 있습니다.
ex . data = {
	"name": "John",
	"age": 30,
	"city": "New York"
}
--> {"name": "John", "age": 30, "city": "New York"}
이렇게 변환된 JSON 문자열은 파일에 저장하거나, HTTP 요청의 본문으로 전송하거나, 다른 프로그램에 전달하는 등의 용도로 사용할 수 있습니다.
이와 반대로, JSON 문자열을 다시 원래의 객체나 데이터로 변환하는 과정을 역직렬화라고 합니다.
 */
