package vehicle.app;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vehicle.app.exceptions.NotFoundException;

@Service
public class VehicleService {
	
	// attributes
	@Autowired
	private VehicleRepository vehicleRepository;
	
	//methods
	public int addVehicle(String make, String model, int year, double consumption) {
		Vehicle v = new Vehicle(make, model, year, consumption);
		int id= vehicleRepository.save(v).getId();
		return id;
	}

	public Iterable<Vehicle> getAllVehicles() {
		
		return vehicleRepository.findAll();
		
	}

	public Vehicle getVehicle(int id) throws NotFoundException {
		return get(id);
	}

	public int updateVehicle(int id, String make, String model, int year, double consumption) throws NotFoundException {
		Vehicle v = get(id);
		v.setMake(make);
		v.setModel(model);
		v.setYear(year);
		v.setConsumption(consumption);
		vehicleRepository.save(v);
		return id;
	}

	public void deleteVehicle(int id) throws NotFoundException {
		vehicleRepository.delete(get(id));
	}
	
	public double simulateTripCost(int id, double fuelCost, double distance) throws NotFoundException {
		return get(id).getConsumption() * fuelCost * distance;
	}
	
	private Vehicle get(int id) throws NotFoundException {
		Optional<Vehicle> v = vehicleRepository.findById(id);
		if(v.isPresent()) {
			return v.get();
		}else {
			throw new NotFoundException("Vehicle with id " + id + " not found!");
		}
	}

}
