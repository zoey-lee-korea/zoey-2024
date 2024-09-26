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