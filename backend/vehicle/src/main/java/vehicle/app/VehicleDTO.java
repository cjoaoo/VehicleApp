package vehicle.app;

import java.io.Serializable;

public class VehicleDTO implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 941962269848660060L;
	
	
	//attributes
	int id;
	String make;
	String model;
	int year;
	Image photo;


	// constructor
	public VehicleDTO(int id, String make, String model, int year, Image photo) {
		this.id = id;
		this.make = make;
		this.model = model;
		this.year = year;
		this.photo = photo;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public int getId() {
		return id;
	}


	public String getMake() {
		return make;
	}


	public String getModel() {
		return model;
	}


	public int getYear() {
		return year;
	}
	
	public Image getPhoto() {
		return photo;
	}

}
