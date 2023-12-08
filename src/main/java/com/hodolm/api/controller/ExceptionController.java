package com.hodolm.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hodolm.api.response.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice	// 모든 컨트롤러에 걸쳐 공통의 예외 처리, 데이터 바인딩, 모델 속성을 정의하는 데 사용됩니다.
public class ExceptionController {

	@ResponseStatus(HttpStatus.BAD_REQUEST)		// 예외 처리 메소드가 호출될 때 HTTP 응답 상태를 BAD_REQUEST (400)로 설정합니다.
	// MethodArgumentNotValidException 예외가 발생했을 때 이 메소드가 처리하도록 지정합니다. 이 예외는 요청 데이터의 검증에 실패했을 때 발생합니다.
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
//		FieldError fieldError = e.getFieldError();
//		String field = fieldError.getField();
//		String message = fieldError.getDefaultMessage();
//		Map<String, String> response = new HashMap<>();
//		response.put(field, message);
		
//		if (e.hasErrors()) {
//			return new ErrorResponse("400", "잘못된 요청입니다.");
//		}
//		return new ErrorResponse("200", "ok");
//		return response;
		ErrorResponse response = 
//				new ErrorResponse("400", "잘못된 요청입니다.");
				ErrorResponse.builder()
				.code("400")
				.message("잘못된 요청입니다.")
				.build();
		
		for (FieldError fieldError : e.getFieldErrors()) {
			response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return response;
		
	}
}
