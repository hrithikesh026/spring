package com.example.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.dto.UserAddressDto;
import com.example.entities.UserAddress;

@Mapper
public interface UserAddressMapper {
	UserAddressMapper INSTANCE = Mappers.getMapper(UserAddressMapper.class);
	
	
	@Mapping(target = "addressId", ignore = true)
	UserAddress DtoToEntity( UserAddressDto userAddressDTO );
	
	
	UserAddressDto entityToDto(UserAddress userAddress);
}
