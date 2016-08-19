package com.raychen518.study.refactoring.thefirstcase.after;

abstract class Price {

	abstract int getPriceCode();

	abstract double getCharge(int daysRented);

	int getFrequentRenterPoints(int daysRented) {
		return 1;
	}

}
