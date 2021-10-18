package vehicle.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Image {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	@Column(nullable = false) 
	String img;
	
	

}
