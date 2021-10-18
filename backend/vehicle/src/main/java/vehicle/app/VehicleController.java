package vehicle.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import vehicle.app.exceptions.NotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class VehicleController {
	
	//attributes
	@Autowired
	private VehicleService vehicleService;
	private final String endpoint = "/api/vehicles";
	
	@PostMapping(endpoint)
	public int addVehicle(
			@RequestParam("make") String make, @RequestParam("model") String model, 
			@RequestParam("year") int year, @RequestParam("consumption") double consumption) {
		return vehicleService.addVehicle(make, model, year, consumption);
	}
	
	@GetMapping(endpoint)
	public List<VehicleDTO> getAllVehicles(){
		List<VehicleDTO> vehicles = new ArrayList<>();
		Iterable <Vehicle> res = vehicleService.getAllVehicles();
		for(Vehicle v : res) {
			vehicles.add(v.toDto());
		}
	//	VehicleDTO v = new VehicleDTO(10,"fake vehicle", "fake model", 0000, null);
	//	vehicles.add(v);
		return vehicles;	
	}
	
	@GetMapping(endpoint + "/{id}")
	public Vehicle getVehicle(@PathVariable int id){
		try {
			return vehicleService.getVehicle(id);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(endpoint + "/{id}")
	public int updateVehicle( @PathVariable int id, @RequestParam("make") String make, @RequestParam("model") String model, 
			@RequestParam("year") int year, @RequestParam("consumption") double consumption){
		try {
			return vehicleService.updateVehicle(id, make, model, year, consumption);
		}catch(NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
	
	@DeleteMapping(endpoint + "/{id}")
	public void deleteVehicle(@PathVariable int id) {
		try {
			vehicleService.deleteVehicle(id);

		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	
	}
	
	@GetMapping(endpoint + "/{id}/cost")
	public double simulateTripCost
		(@PathVariable int id, @RequestParam("fuelCost") double fuelCost, @RequestParam("distance") double distance){
			try {
				return vehicleService.simulateTripCost(id, fuelCost, distance);
			} catch (NotFoundException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
			}
	}
	
	
	

}
