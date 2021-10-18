package vehicle.app;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Vehicle {
	
	//attributes
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	@Column(nullable = false)
	String make;
	
	@Column(nullable = false)
	String model;
	
	@Column(nullable = false)
	int year;
	
	@Column(nullable = false)
	double consumption; //average litre consumption per km
	
	@OneToOne(fetch=FetchType.EAGER , cascade=CascadeType.ALL , orphanRemoval=true)
	@JoinColumn(name="ImageId")
	private Image photo;
	
	// constructors
	
	public Vehicle() {
		
	}
	
	public Vehicle(String make, String model, int year, double consumption) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.consumption = consumption;
	}
	
	//getters

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

	public double getConsumption() {
		return consumption;
	}

	public Image getPhoto() {
		return photo;
	}
	
	// setters

	public void setId(int id) {
		this.id = id;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}

	public void setPhoto(Image photo) {
		this.photo = photo;
	}
	
	// method
	public VehicleDTO toDto() {
		return new VehicleDTO(id, model, make, year, photo);
	}

}