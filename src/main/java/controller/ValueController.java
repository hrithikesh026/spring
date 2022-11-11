package controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "value")
public class ValueController {
	
	@Value("${path}")
	private String pathString;
	
	@Value("string value")
	private String simpleString;
	
	@Value("${unknown.param:some default}")
	private String defaultString;
	
	@GetMapping("get_value")
	public String getValue() {
		return defaultString;
	}
	
}
