package com.rgt.rentalsystem.rental.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.rgt.rentalsystem.entity.Customer;
import com.rgt.rentalsystem.entity.Rental;
import com.rgt.rentalsystem.entity.Vehicle;

public class RentalService implements RentalCostCalculator {
	
	private List<Vehicle> vehicles;
    private List<Rental> rentals;
   
    
    public RentalService() {
        vehicles = new ArrayList<>();
        rentals = new ArrayList<>();
    }

	//  rentalSystem.addVehicle(car);
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);    
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }
	

	@Override
	public BigDecimal calculateRentalCost(Rental rental) {
		    Duration duration = Duration.between(rental.getStartTime(), rental.getEndTime());
	        long hours = duration.toHours();
	        BigDecimal hourlyRate = BigDecimal.valueOf(10); 
	        return hourlyRate.multiply(BigDecimal.valueOf(hours));
	}
	
	// Display available vehicles
    public List<Vehicle> getAvailableVehicles() {
    	List<Vehicle> list=new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.isAvailable()) {
              list.add(vehicle);
            }
        }
		return list;  
    }
    public Vehicle getVehicleByLicensePlate(String licensePlate) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getLicensePlate().equals(licensePlate)) {
                return vehicle;
            }
        }
        return null;
    }
    
    public Rental getRentalById(String rentalId) {
        for (Rental rental : rentals) {
            if (rental.getId().equals(rentalId)) {
                return rental;
            }
        }
        return null;
    }

    // Rent a vehicle
    public Rental rentVehicle(Customer customer, Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime) {
        if (vehicle.isAvailable()) {
            Rental rental = new Rental(vehicle, customer, startTime, endTime);
            rentals.add(rental);
            vehicle.setAvailable(false);
            return rental;
        } else {
            return null;
        }
    }

 // Return a rented vehicle
    public boolean returnVehicle(Rental rental) {
    	if (rentals.contains(rental)) {
            rentals.remove(rental);
            rental.getRentedVehicle().setAvailable(true);
            return true;
        } else {
            return false; 
        }
    }
}
