package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Car;
import com.example.entities.CarRecord;
import com.example.repositories.CarRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vehiclepackage.CarType;
import vehiclepackage.VehicleInterface;

//@RestController
@Slf4j
@RequestMapping(value = "vehicle")
public class VehicleController {
	
//	@Autowired
//	private CarRepository carRepository;
	
	
	@Autowired
//	@Qualifier("truck")
	private VehicleInterface vehicle;
	
	@GetMapping("start")
	public String startVehicle() {
		return vehicle.start();
	}
	
	@PostMapping("add_car")
	public CarRecord addCar() {
		CarRecord carRecord = new CarRecord(10, "swift desire", CarType.SEDAN);
		return carRepository.save(carRecord);
	}
	
	@DeleteMapping("delete_car/{id}")
	public String addCar(@PathVariable Integer id) {
		carRepository.deleteById(id);
		return "deleted car with id "+id;
	}
	
}
