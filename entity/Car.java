package com.rgt.rentalsystem.entity;

public class Car extends Vehicle{
	    private int numberOfSeats;

	    public Car(String licensePlate, String make, String model, int numberOfSeats) {
	        super(licensePlate, make, model);
	        this.numberOfSeats = numberOfSeats;
	    }

		public Car(String licensePlate, String make, String model) {
			super(licensePlate, make, model);
			// TODO Auto-generated constructor stub
		}

}
