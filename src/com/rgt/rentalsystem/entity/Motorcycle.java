package com.rgt.rentalsystem.entity;

public class Motorcycle extends Vehicle{
	
	public Motorcycle(String licensePlate, String make, String model, boolean isAvailable) {
		super(licensePlate, make, model);
	}
	
	public Motorcycle(String licensePlate, String make, String model, boolean isAvailable, Integer motorcycleId,
			String motorcycleName, String licenseType) {
		super(licensePlate, make, model);
		this.motorcycleId = motorcycleId;
		this.motorcycleName = motorcycleName;
		this.licenseType = licenseType;
	}

	private Integer motorcycleId;
	private String motorcycleName;
	private String licenseType;
	
	
	public Integer getMotorcycleId() {
		return motorcycleId;
	}
	public void setMotorcycleId(Integer motorcycleId) {
		this.motorcycleId = motorcycleId;
	}
	public String getMotorcycleName() {
		return motorcycleName;
	}
	public void setMotorcycleName(String motorcycleName) {
		this.motorcycleName = motorcycleName;
	}
	public String getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

}
