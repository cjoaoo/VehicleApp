package vehicle.app.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import vehicle.app.data.Image;
import vehicle.app.data.Vehicle;
import vehicle.app.exceptions.NotFoundException;
import vehicle.app.facade.ImageDTO;
import vehicle.app.facade.VehicleDTO;
import vehicle.app.service.VehicleService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class VehicleController {
	
	//attributes
	@Autowired
	private VehicleService vehicleService;
	private final String endpoint = "/api/vehicles";
	
	// http methods
	
	/**
	 * @param make
	 * @param model
	 * @param year
	 * @param consumption
	 * @return
	 */
	@PostMapping(endpoint)
	public int addVehicle(
			@RequestParam("make") String make, @RequestParam("model") String model, 
			@RequestParam("year") int year, @RequestParam("consumption") double consumption) {
		return vehicleService.addVehicle(make, model, year, consumption);
		
	}
	
	/**
	 * @return
	 */
	@GetMapping(endpoint)
	public List<VehicleDTO> getAllVehicles(){
		List<VehicleDTO> vehicles = new ArrayList<>();
		Iterable <Vehicle> res = vehicleService.getAllVehicles();
		for(Vehicle v : res) {
			vehicles.add(v.toDto());
		}
		return vehicles;	
	}
	
	/**
	 * @param id
	 * @return
	 */
	@GetMapping(endpoint + "/{id}")
	public Vehicle getVehicle(@PathVariable int id){
		try {
			return vehicleService.getVehicle(id);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	/**
	 * @param id
	 * @param make
	 * @param model
	 * @param year
	 * @param consumption
	 * @return
	 */
	@PutMapping(endpoint + "/{id}")
	public int updateVehicle(@PathVariable int id, @RequestParam("make") String make, @RequestParam("model") String model, 
			@RequestParam("year") int year, @RequestParam("consumption") double consumption){
		try {
			return vehicleService.updateVehicle(id, make, model, year, consumption);
		}catch(NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
	
	/**
	 * @param id
	 */
	@DeleteMapping(endpoint + "/{id}")
	public void deleteVehicle(@PathVariable int id) {
		try {
			vehicleService.deleteVehicle(id);

		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	
	}
	
	/**
	 * @param id
	 * @param fuelCost
	 * @param distance
	 * @return
	 */
	@GetMapping(endpoint + "/{id}/cost")
	public double simulateTripCost
		(@PathVariable int id, @RequestParam("fuelCost") double fuelCost, @RequestParam("distance") double distance){
			try {
				return vehicleService.simulateTripCost(id, fuelCost, distance);
			} catch (NotFoundException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
			}
	}
	
	/**
	 * @param vehicleId
	 * @param name
	 * @param file
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(endpoint + "/{vehicleId}/img")	
	public void uploadImage(@PathVariable int vehicleId, @RequestParam("imageFile") MultipartFile file) {
		try {
			Image img = new Image(file.getBytes());
			vehicleService.updateImage(vehicleId, img);
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	/**
	 * @param id
	 */
	
	@DeleteMapping(endpoint + "/{vehicleId}/img")
	public void removeImage(@PathVariable int vehicleId) {
		try {
			vehicleService.removeImage(vehicleId);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	/**
	 * @param id
	 * @return
	 */
	@GetMapping(endpoint + "/{vehicleId}/img")
	public ImageDTO getImage(@PathVariable int vehicleId){

		try {
			Image img = vehicleService.getVehicleImage(vehicleId);
			if(img == null) {
				throw new NotFoundException("This vehicle has no image.");
			}			
			
			return img.toDTO();
			//return img;
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}


	}
	
	

}
