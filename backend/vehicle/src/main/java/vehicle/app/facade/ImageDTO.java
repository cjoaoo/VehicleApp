package vehicle.app.facade;

import java.io.Serializable;

public class ImageDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5743854291487113796L;
	
	int id;
	String name;
	byte[] src;
	
	public ImageDTO(int id, String name, byte[] src) {
		this.id = id;
		this.name = name;
		this.src = src;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public byte[] getSrc() {
		return src;
	}


}
