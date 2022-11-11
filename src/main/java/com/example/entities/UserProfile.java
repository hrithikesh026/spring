package com.example.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer profileId;
	
	private String profileName;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private UserRecord userRecord;
	
	@Override
	public String toString() {
		return "UserProfile [profileId=" + profileId + ", profileName=" + profileName + "]";
	}
	
}
