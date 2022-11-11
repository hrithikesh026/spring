package com.example.dto;

import javax.validation.constraints.NotEmpty;

import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.example.entities.UserAddress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Scope("Prototype")
public class UserAddressDto {
	@NotEmpty
	private String line1;

	private String line2;
}
