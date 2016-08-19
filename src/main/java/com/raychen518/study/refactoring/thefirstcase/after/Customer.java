package com.raychen518.study.refactoring.thefirstcase.after;

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
		String result = "Rental Record for " + name + "\n";

		for (Rental rental : rentals) {
			result += "\t" + rental.getMovie().getTitle() + "\t" + rental.getCharge() + "\n";
		}

		result += "Charge Owed: " + getTotalCharge() + "\n";
		result += "Frequent Renter Points Earned: " + getTotalFrequentRenterPoints();

		return result;
	}

	public String htmlStatement() {
		String result = "<h1>Rental Record for <em>" + name + "</em></h1><p />";

		for (Rental rental : rentals) {
			result += rental.getMovie().getTitle() + ": " + rental.getCharge() + "<br />";
		}

		result += "<p />Charge Owed: <em>" + getTotalCharge() + "</em>";
		result += "Frequent Renter Points Earned: <em>" + getTotalFrequentRenterPoints() + "</em>";

		return result;
	}

	private double getTotalCharge() {
		double result = 0;

		for (Rental rental : rentals) {
			result += rental.getCharge();
		}

		return result;
	}

	private int getTotalFrequentRenterPoints() {
		int result = 0;

		for (Rental rental : rentals) {
			result += rental.getFrequentRenterPoints();
		}

		return result;
	}

}
