package com.example.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NamedQuery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.type.filter.AbstractClassTestingTypeFilter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@NamedQuery(name = "UserRecord.findAllUsersWithIdGreaterThan",
		query="SELECT u FROM UserRecord u WHERE u.id > ?1")
@NamedNativeQuery(name="UserRecord.findAllUsersWithIdLessThan",
		query = "SELECT * FROM user_record u WHERE u.id < ?1",
		resultClass = UserRecord.class)
public class UserRecord {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue
	@Setter
	private Integer id;   


	@NotEmpty(message = "Name cannot be empty")
	@NotNull
	@Setter
	@Column( updatable = false)
	private String name;  
	
	@NotEmpty(message = "Email cannot be null")
	@Email(message = "Invalid Email")
	@Setter
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_address_id", referencedColumnName = "addressId")
	@Setter
	private UserAddress userAddress;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_record_id")
	@Setter
	@JsonIgnoreProperties("userRecord")
	private List<UserProfile> profiles;
	
	public UserRecord(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
}
