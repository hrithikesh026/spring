package com.example.dto;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.config.Profiles;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.example.entities.UserProfile;

//import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@Scope("prototype")
public class UserRecordDto {
	@NotEmpty(message = "Name cannot be empty")
	private String name;  

	@NotEmpty(message = "Email cannot be null")
	@Email(message = "Invalid Email")
	private String email;
	
	@NotNull
	private UserAddressDto userAddressDto;
	
//	@NotNull
	private List<UserProfile> profilesDto;

}
