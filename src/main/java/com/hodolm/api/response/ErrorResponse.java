package com.hodolm.api.response;

import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
 * {
 * 		"code": "400",
 * 		"message": "잘못된 요청입니다.",
 * 		"validation": {
 * 			"title": "값을 입력해주세요."
 * 		}
 * }
 */
@Getter
public class ErrorResponse {

	private final String code;
	private final String message;
	private final Map<String, String> vaildation = new HashMap<>();
	
	public void addValidation(String field, String defaultMessage) {
		this.vaildation.put(field, defaultMessage);
	}

	@Builder
	public ErrorResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
