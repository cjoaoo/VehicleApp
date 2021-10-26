package vehicle.app.data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import vehicle.app.facade.VehicleDTO;

@Entity
@Table(name = "vehicle", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
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
	
	//
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY, mappedBy="vehicle")
	@JsonIgnoreProperties("vehicle")
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
	
	public boolean hasPhoto() {
		return photo != null;
	}
	
	public VehicleDTO toDto() {
		return new VehicleDTO(id, model, make, year);
	}

}
