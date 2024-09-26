package com.example.zoey;

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
		return createSuccessResponse(zoeyConstants.DEFAULT_SUCCESS_CODE, zoeyConstants.DEFAULT_SUCCESS_DESC, data, httpStatus);
	}

	public static ResponseEntity<ResponseDto> createSuccessResponse(String statusCode, String description, Object data,
			HttpStatus httpStatus) {
		return new ResponseEntity<>(createSuccessResponseData(statusCode, description, data), httpStatus);
	}

	private static ResponseDto createSuccessResponseData(String statusCode, String description, Object data) {
		if (data == null)
			data = new EmptyJsonResponse();

		return ResponseDto.builder().successOrNot(zoeyConstants.SUCCESS_FLAG).statusCode(statusCode).data(data)
				.build();
	}

	// GET
	public static ResponseEntity<ResponseDto> createGetSuccessResponse(Object data) {
		return createSuccessResponse(data, HttpStatus.OK);
	}

}
