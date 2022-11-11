package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.examples.listeners.CarListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vehiclepackage.CarType;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(CarListener.class)
public class CarRecord{
	@Id
	@GeneratedValue()
	private Integer carId;
	
	private String carName;
	
	@Enumerated(EnumType.STRING)
	private CarType carType;
	
}