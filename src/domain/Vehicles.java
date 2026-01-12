package domain;

import java.time.Year;

public class Vehicles {
	
	private String plate, brand, model, propertyOwner, year;
	private String fuelType;
	
	public Vehicles() {}
		
	public Vehicles(String plate, String brand, String model, String propertyOwner, String year, String fuelType) {
		super();
		this.plate = plate;
		this.brand = brand;
		this.model = model;
		this.propertyOwner = propertyOwner;
		this.year = year;
		this.fuelType = fuelType;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPropertyOwner() {
		return propertyOwner;
	}

	public void setPropertyOwner(String propertyOwner) {
		this.propertyOwner = propertyOwner;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	@Override
	public String toString() {
		return "Vehicles [plate=" + plate + ", brand=" + brand + ", model=" + model + ", propertyOwner=" + propertyOwner
				+ ", year=" + year + ", fuelType=" + fuelType + "]";
	}
	
}
