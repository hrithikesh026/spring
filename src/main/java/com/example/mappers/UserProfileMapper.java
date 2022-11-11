package com.example.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.dto.UserProfileDto;
import com.example.entities.UserProfile;

@Mapper
public interface UserProfileMapper {
	UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);
	
	
	UserProfile DtoToEntity( UserProfileDto userProfileDTO );
	
	
	UserProfileDto entityToDto(UserProfile userProfile); 
}
