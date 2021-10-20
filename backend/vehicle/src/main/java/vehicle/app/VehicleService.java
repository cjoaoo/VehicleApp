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
	/**
	 * @param make
	 * @param model
	 * @param year
	 * @param consumption
	 * @return
	 */
	public int addVehicle(String make, String model, int year, double consumption) {
		Vehicle v = new Vehicle(make, model, year, consumption);
		int id= vehicleRepository.save(v).getId();
		return id;
	}

	/**
	 * @return
	 */
	public Iterable<Vehicle> getAllVehicles() {
		
		return vehicleRepository.findAll();
		
	}

	/**
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	public Vehicle getVehicle(int id) throws NotFoundException {
		return get(id);
	}

	/**
	 * @param id
	 * @param make
	 * @param model
	 * @param year
	 * @param consumption
	 * @return
	 * @throws NotFoundException
	 */
	public int updateVehicle(int id, String make, String model, int year, double consumption) throws NotFoundException {
		Vehicle v = get(id);
		if(!make.isEmpty()) {
			v.setMake(make);
		}
		if(!model.isEmpty()) {
			v.setModel(model);
		}
		if(year > 0) {
			v.setYear(year);
		}
		if(consumption > 0) {
			v.setConsumption(consumption);
		}
		vehicleRepository.save(v);
		return id;
		
	}

	/**
	 * @param id
	 * @throws NotFoundException
	 */
	public void deleteVehicle(int id) throws NotFoundException {
		vehicleRepository.delete(get(id));
	}
	
	/**
	 * @param id
	 * @param fuelCost
	 * @param distance
	 * @return
	 * @throws NotFoundException
	 */
	public double simulateTripCost(int id, double fuelCost, double distance) throws NotFoundException {
		return get(id).getConsumption() * fuelCost * distance;
	}
	
	/**
	 * @param id
	 * @param img
	 * @throws NotFoundException 
	 */
	public void updateImage(int id, Image img) throws NotFoundException {
		Vehicle v = get(id);
		img.setName(v.getMake() + " " + v.getModel());
		//img.setVehicle(v);
		v.setPhoto(img);
		vehicleRepository.save(v);
	}
	
	/**
	 * @param vehicleId
	 * @return
	 * @throws NotFoundException 
	 */
	public Image getVehicleImage(int vehicleId) throws NotFoundException {
		return get(vehicleId).getPhoto();
	}
	
	public void removeImage(int vehicleId) throws NotFoundException {
		Vehicle v = get(vehicleId);
		v.setPhoto(null);
		vehicleRepository.save(v);
	}
	
	
	// private methods
	
	/**
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	private Vehicle get(int id) throws NotFoundException {
		Optional<Vehicle> v = vehicleRepository.findById(id);
		if(v.isPresent()) {
			return v.get();
		}else {
			throw new NotFoundException("Vehicle with id " + id + " not found!");
		}
	}





}
