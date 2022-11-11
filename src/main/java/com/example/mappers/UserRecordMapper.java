package com.example.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.dto.UserRecordDto;
import com.example.entities.UserRecord;


@Mapper
public interface UserRecordMapper {
	UserRecordMapper INSTANCE = Mappers.getMapper(UserRecordMapper.class);
	
	@Mapping(source = "userAddressDto", target = "userAddress")
	@Mapping(source = "profilesDto", target = "profiles")
	UserRecord dtoToEntity( UserRecordDto userRecordDto );
	
	@Mapping(source = "userAddress", target = "userAddressDto")
	@Mapping(source = "profiles", target = "profilesDto")
	UserRecordDto entityToDto(UserRecord userRecord);
}
