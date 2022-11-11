package com.example.advice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.repositories.exceptions.StudentIdNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class AppExceptionHandler {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleInvalidInformation(MethodArgumentNotValidException ex){
		ex.printStackTrace();
		log.debug("Exception occured");
		Map<String, String> errorMap = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach(err->{
			errorMap.put(err.getCode(), err.getDefaultMessage());
		});
		return errorMap;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(StudentIdNotFoundException.class)
	public Map<String, String> handleStudentIdNotFoundException(StudentIdNotFoundException ex){
		ex.printStackTrace();
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("Error_Message", ex.getLocalizedMessage());
		return errorMap;
	}

}
