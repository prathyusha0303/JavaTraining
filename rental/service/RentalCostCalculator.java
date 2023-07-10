package com.rgt.rentalsystem.rental.service;

import java.math.BigDecimal;

import com.rgt.rentalsystem.entity.Rental;

public interface RentalCostCalculator {

	BigDecimal calculateRentalCost(Rental rental);
	
}
