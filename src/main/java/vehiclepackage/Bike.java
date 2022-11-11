package vehiclepackage;

import org.springframework.stereotype.Component;

@Component("bike")
public class Bike implements VehicleInterface{

	@Override
	public String start() {
		return "Bike is starting";
	}

}
