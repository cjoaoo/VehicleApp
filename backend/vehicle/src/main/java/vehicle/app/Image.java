package vehicle.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Image {
	
	// attributes
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	@Column 
	private String name;
	
	@Column (nullable = false, length = 1000)
	private byte[] img;
	
	//@OneToOne
    //@JoinColumn(name = "vehicle_id")
	//private Vehicle vehicle;
	
	
	// constructors
	
	public Image() {
		super();
	}
	
	public Image(byte[] img) {
		this.img = img;
	}
	
	public Image(String name, byte[] img, Vehicle vehicle) {
		this.name = name;
		this.img = img;
		//this.vehicle = vehicle;
	}
	
	// getters

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public byte[] getImg() {
		return img;
	}
	/*
	public Vehicle getVehicle() {
		return vehicle;
	}
*/
	// setters

	public void setName(String name) {
		this.name = name;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}
	/*
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	*/
	

	
}
