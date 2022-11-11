package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.UserRecordDto;
import com.example.entities.UserAddress;
import com.example.entities.UserProfile;
import com.example.entities.UserRecord;
import com.example.mappers.UserProfileMapper;
import com.example.mappers.UserRecordMapper;
import com.example.repositories.UserRepository;
import com.example.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	private UserRecordMapper userRecordMapper;
	
	private UserRecord userRecord;
	private UserRecordDto userRecordDto;
	
//	@PostMapping("user")
//	public ResponseEntity<String> addUser(@Valid @RequestBody UserRecordDto userRecord) {
//		
//		UserRecord addedUserRecord = userService.addUser(userRecord);
//		if(addedUserRecord == null) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists with id");
//		}
//		return ResponseEntity.status(HttpStatus.OK)
//				.header("Respose-Header", "Add User Header")
//				.body("Inserted Record = "+addedUserRecord );
//		
//	}
	
	@GetMapping("user")
	public ResponseEntity<List<UserRecordDto>> getUsers(){
		List<UserRecordDto> userRecordDtos = userService.getAllUsers();
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Custom-Header", "Custom Header Value");
		return new ResponseEntity<List<UserRecordDto>>(userRecordDtos, headers, HttpStatus.OK) ;
	}
	
	@GetMapping("user/{id}")
	public ResponseEntity<UserRecordDto> getUserById(@PathVariable int id,HttpServletResponse response){
		log.debug("GetUserById");
		userRecordDto = userService.getUserWithId(id);
		response.addHeader("custom-header", "get user by id");
		if(userRecordDto == null)
			return new ResponseEntity<UserRecordDto>( HttpStatus.BAD_REQUEST);
		return new ResponseEntity<UserRecordDto>( userRecordDto, HttpStatus.OK);
	}
	
	@GetMapping("get_user")
	public ResponseEntity<UserRecordDto> getUserByIdParam(@RequestParam int id, @RequestHeader(value="User-Agent") String userAgent){
		log.info("User agent is: {}", userAgent);
		userRecordDto = userService.getUserWithId(id);
		if(userRecordDto == null)
			return new ResponseEntity<UserRecordDto>(HttpStatus.BAD_REQUEST) ;
		return new ResponseEntity<UserRecordDto>(userRecordDto, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value ="user/{id}" ,method = RequestMethod.PUT)
	public ResponseEntity<UserRecord> updateUser(@Valid @RequestBody UserRecordDto userRecord,@PathVariable int id){
		UserRecord updatedUserRecord = userService.updateUserWithId(id, userRecord);
		if(updatedUserRecord == null)
			return new ResponseEntity<UserRecord>( HttpStatus.BAD_REQUEST) ;
		return new ResponseEntity<UserRecord>(updatedUserRecord, HttpStatus.OK) ;
	}
	
	@ResponseBody
	@RequestMapping(value ="user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> DeleteUser(@PathVariable int id){
		if(userService.deleteUserWithId(id)) {
			return new ResponseEntity<String>("User with id "+id+" deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Could not delete user with id "+id, HttpStatus.OK) ;
	}
	
	@PostMapping("user")
	public ResponseEntity<UserRecord> addUser(@Valid @RequestBody UserRecordDto userRecordDtoToBeAdded){

		userRecord = UserRecordMapper.INSTANCE.dtoToEntity(userRecordDtoToBeAdded);
		List<UserProfile> profileList = new ArrayList<>();
		
		userRecordDtoToBeAdded.getProfilesDto().forEach(profileDto->{
			profileList.add( profileDto);
		});
		
		userRecord.setProfiles(profileList);
		
		log.info(userRecord.toString());

		return new ResponseEntity<>(userService.addUser(userRecord), HttpStatus.OK) ;
	}
	
	@GetMapping("get_user_by_name")
	public ResponseEntity<List<UserRecordDto>> SearchByName(@RequestParam String name){
		List<UserRecordDto> resultDtos = userService.getUserWithName(name);
		return new ResponseEntity<List<UserRecordDto>>(resultDtos, HttpStatus.OK);
	}
	
	@GetMapping("get_users_with_pagination")
	public ResponseEntity<List<UserRecordDto>> getWithPagination(@RequestParam int pageSize){
		List<UserRecordDto> resultDtos = userService.getUsersWithPagination(pageSize);
		return new ResponseEntity<>( resultDtos, HttpStatus.OK);
	}
	
	@GetMapping("get_users_greater_than_id")
	public ResponseEntity<List<UserRecordDto>> getUserWithIdGreaterThan(@RequestParam int id){
		List<UserRecordDto> resultDtos = userService.getAllUsersWithIdGreaterThan(id);
		return new ResponseEntity<>( resultDtos, HttpStatus.OK);
	}
	
	@GetMapping("get_users_less_than_id")
	public ResponseEntity<List<UserRecordDto>> getUserWithIdLessThan(@RequestParam int id){
		List<UserRecordDto> resultDtos = userService.getAllUsersWithIdLessThan(id);
		return new ResponseEntity<>( resultDtos, HttpStatus.OK);
	}
	
}
