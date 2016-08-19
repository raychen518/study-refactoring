package com.raychen518.study.refactoring.thefirstcase.before;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {

	private String name;
	private Vector<Rental> rentals = new Vector<>();

	public Customer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addRental(Rental rental) {
		rentals.addElement(rental);
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Enumeration<Rental> rentalElements = rentals.elements();
		String result = "Rental Record for " + getName() + "\n";
		while (rentalElements.hasMoreElements()) {
			double thisAmount = 0;
			Rental each = (Rental) rentalElements.nextElement();

			switch (each.getMovie().getPriceCode()) {
			case Movie.REGULAR:
				thisAmount += 2;
				if (each.getDaysRented() > 2) {
					thisAmount += (each.getDaysRented() - 2) * 1.5;
				}
				break;

			case Movie.NEW_RELEASE:
				thisAmount += each.getDaysRented() * 3;
				break;

			case Movie.CHILDREN:
				thisAmount += 1.5;
				if (each.getDaysRented() > 3) {
					thisAmount += (each.getDaysRented() - 3) * 1.5;
				}
				break;
			}

			frequentRenterPoints++;
			if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE && each.getDaysRented() > 1) {
				frequentRenterPoints++;
			}

			result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;
		}

		result += "Amount Owed: " + totalAmount + "\n";
		result += "Frequent Renter Points Earned: " + frequentRenterPoints;

		return result;
	}

}
