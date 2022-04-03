package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

//	@ExceptionHandler(value=IllegalArgumentException.class)
//	public String handleArgumentException(IllegalArgumentException e) {
	@ExceptionHandler(value=Exception.class)
	// 모든 Exception 받을 수 있도록 처리
	public String handleArgumentException(Exception e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
}
