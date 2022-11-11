package com.example.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.tomcat.jni.Address;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.availability.ApplicationAvailabilityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.repositories.UserRepository;
import com.example.repositories.exceptions.StudentIdNotFoundException;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice.OffsetMapping.Sort;
import userRepository.save;

import com.example.demo.NewSpringProjectApplication;
import com.example.dto.UserAddressDto;
import com.example.dto.UserRecordDto;
import com.example.entities.*;
import com.example.mappers.UserAddressMapper;
import com.example.mappers.UserRecordMapper;

@Service
@Slf4j
public class UserService {
	@Autowired    
	private UserRepository userRepository;    
	
	@Autowired
	ApplicationContext applicationContext ;

	private UserRecord userRecord;
	
	public List<UserRecordDto> getAllUsers()  {
		
		return userRepository.findAll().stream()
				.map((userRecord)->{
					log.debug("This is findall service function");
					UserRecordDto userRecordDto = UserRecordMapper.INSTANCE.entityToDto(userRecord);
					return  userRecordDto;
				})
				.collect(Collectors.toList()); 
	}
//	public UserRecord addUser(UserRecordDto userRecordToBeInserted)  {    
//		UserRecord userRecord = userRepository.save(applicationContext.getBean(UserRecord.class, userRecordToBeInserted.getName(),userRecordToBeInserted.getEmail()));
//		return userRecord;
//	}
	
//	public UserRecord addUser(UserRecordDto userRecordToBeInserted) {
//		String name = userRecordToBeInserted.getName();
//		String email = userRecordToBeInserted.getEmail();
//		UserAddress userAddress = userRecordToBeInserted.getUserAddressDto();
//		userRecord = applicationContext.getBean(UserRecord,user)
//		userRepository.save(null)
//	}
	
	public UserRecord addUser(UserRecord userRecordToBeInserted) {
		log.debug(userRecordToBeInserted.toString());
		List<UserProfile> uProfiles = new ArrayList<>();
		userRecordToBeInserted.getProfiles().forEach((profile)->{
//			uProfiles.add(applicationContext.getBean(UserProfile.class,profile.getProfileId(),profile.getProfileName(),profile.getUserRecord()));
			uProfiles.add(new UserProfile( profile.getProfileId(), profile.getProfileName(),profile.getUserRecord()));
		});
		userRecordToBeInserted.setProfiles(uProfiles);
		return userRepository.save(userRecordToBeInserted);
	}
	
	public UserRecordDto getUserWithId(int id) {
		Optional<UserRecord> userRecOptional = userRepository.findById(id);
		log.debug(userRecOptional.get().toString());
		if(userRecOptional.isPresent()) {
			UserRecord userRecord = userRecOptional.get();
			return UserRecordMapper.INSTANCE.entityToDto(userRecord);
			
		}
		throw new StudentIdNotFoundException(id);
	}
	
	public UserRecord updateUserWithId(int id, UserRecordDto userRecordToBeUpdated) {
		Optional<UserRecord> savedRecord = userRepository.findById(id);
		
		if(savedRecord.isPresent()) {
			savedRecord.get().setEmail(userRecordToBeUpdated.getEmail());
			savedRecord.get().setName(userRecordToBeUpdated.getName());
			
			return userRepository.save(savedRecord.get());
		}
		throw new StudentIdNotFoundException(id);
	}
	
	public boolean deleteUserWithId(int id) {
		Optional<UserRecord> userRecord = userRepository.findById(id);
		if(userRecord.isEmpty()) {
			throw new StudentIdNotFoundException(id);
		}

		try {
			userRepository.deleteById(id);
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public List<UserRecordDto> getUserWithName(String name){
		List<UserRecord> userRecords = userRepository.findAllUsersWithNameLike(name);
		return userRecords.stream().map(userRecord->UserRecordMapper.INSTANCE.entityToDto(userRecord)).collect(Collectors.toList());
	}
	
	public List<UserRecordDto> getUsersWithPagination(int pageSize){
		List<UserRecordDto> userRecords = userRepository.findAllUsersWithPaginationJPQL(Pageable.ofSize(pageSize))
				.stream().map(userRecord->UserRecordMapper.INSTANCE.entityToDto(userRecord))
				.collect(Collectors.toList());
		return userRecords;
	}
	
	public List<UserRecordDto> getAllUsersWithIdGreaterThan(int id){
		List<UserRecordDto> userRecords = userRepository.findAllUsersWithIdGreaterThan(id)
				.stream().map(userRecord->UserRecordMapper.INSTANCE.entityToDto(userRecord))
				.collect(Collectors.toList());
		return userRecords;
	}
	
	public List<UserRecordDto> getAllUsersWithIdLessThan(int id){
		List<UserRecordDto> userRecords = userRepository.findAllUsersWithIdLessThan(id)
				.stream().map(userRecord->UserRecordMapper.INSTANCE.entityToDto(userRecord))
				.collect(Collectors.toList());
		return userRecords;
	}
	
}
