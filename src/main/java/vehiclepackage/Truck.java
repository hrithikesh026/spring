package vehiclepackage;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("truck")
@Primary
public class Truck implements VehicleInterface{

	@Override
	public String start() {
		return "Truck is starting";
	}

}
