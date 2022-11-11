package controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "company")
public class CompanyController {
	
	@PostMapping("add_company")
	public String addCompany() {
		
		
		return "Company added";
		
	}
}
