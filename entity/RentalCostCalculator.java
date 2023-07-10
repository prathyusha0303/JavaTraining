package com.rgt.rentalsystem.entity;

import java.math.BigDecimal;

public interface RentalCostCalculator {

	BigDecimal calculateRentalCost(Rental rental);
}
