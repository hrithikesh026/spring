package vehiclepackage;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component("car")
public class Car implements VehicleInterface{
	@Id
	@GeneratedValue()
	private Integer carId;
	
	@Enumerated(EnumType.ORDINAL.STRING)
	private CarType carType;

	@Override
	public String start() {
		return "Car is starting";
	}
	
}
