package com.raychen518.study.refactoring.thefirstcase.after;

class ChildrenPrice extends Price {

	@Override
	int getPriceCode() {
		return Movie.CHILDREN;
	}

	@Override
	double getCharge(int daysRented) {
		double result = 1.5;

		if (daysRented > 3) {
			result += (daysRented - 3) * 1.5;
		}

		return result;
	}

}
