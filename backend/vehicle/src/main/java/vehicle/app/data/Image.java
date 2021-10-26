package vehicle.app.data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import vehicle.app.facade.ImageDTO;

import javax.persistence.ForeignKey;

@Entity
@Table(name = "image", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Image {
	
	// attributes

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	@Column 
	private String name;
	
	@Column (nullable = false, length = 1000)
	private byte[] img;
	
	// @JoinColumn(name = "vehicle_id" is the name of the column
	// foreignKey = @ForeignKey(name = "FK_vehicle_image") is the name of the constraint
	
	@OneToOne(fetch=FetchType.LAZY) @JoinColumn(name = "vehicle_id", nullable = false, foreignKey = @ForeignKey(name = "FK_vehicle_image"))
	@JsonIgnoreProperties("photo")
	private Vehicle vehicle;
	
	
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
	
	public Vehicle getVehicle() {
		return vehicle;
	}

	// setters

	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}
	
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	
	public ImageDTO toDTO() {
		return new ImageDTO(id, name, img);
	}
	

	
}
