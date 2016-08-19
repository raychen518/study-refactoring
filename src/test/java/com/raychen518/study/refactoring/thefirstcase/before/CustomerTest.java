package com.raychen518.study.refactoring.thefirstcase.before;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerTest {

	private static final int[] MOVIE_PRICE_CODES = { Movie.REGULAR, Movie.NEW_RELEASE, Movie.CHILDREN };
	private static final int DEFAULT_MAX_RENTAL_DAYS_RENTED = 14;
	private static final String DEFAULT_CUSTOMER_NAME = "Ray Chen";

	private Customer customer;

	@Before
	public void setup() {
		customer = new Customer(DEFAULT_CUSTOMER_NAME);
	}

	@Test
	public void testStatement01Renting1RegularForNoMoreThan2Days() {
		List<Rental> rentals = Arrays
				.asList(new Rental(new Movie(getRandomMovieTitle(), Movie.REGULAR), getRandomRentalDaysRented(-2)));
		for (Rental rental : rentals) {
			customer.addRental(rental);
		}

		String customerStatement = customer.statement();

		assertEquals(getCustomerStatement(rentals), customerStatement);
	}

	@Test
	public void testStatement02Renting1RegularForMoreThan2Days() {
		List<Rental> rentals = Arrays
				.asList(new Rental(new Movie(getRandomMovieTitle(), Movie.REGULAR), getRandomRentalDaysRented(2)));
		for (Rental rental : rentals) {
			customer.addRental(rental);
		}

		String customerStatement = customer.statement();

		assertEquals(getCustomerStatement(rentals), customerStatement);
	}

	@Test
	public void testStatement03Renting1NewRelease() {
		List<Rental> rentals = Arrays
				.asList(new Rental(new Movie(getRandomMovieTitle(), Movie.NEW_RELEASE), getRandomRentalDaysRented()));
		for (Rental rental : rentals) {
			customer.addRental(rental);
		}

		String customerStatement = customer.statement();

		assertEquals(getCustomerStatement(rentals), customerStatement);
	}

	@Test
	public void testStatement04Renting1ChildrenForNoMoreThan3Days() {
		List<Rental> rentals = Arrays
				.asList(new Rental(new Movie(getRandomMovieTitle(), Movie.CHILDREN), getRandomRentalDaysRented(-3)));
		for (Rental rental : rentals) {
			customer.addRental(rental);
		}

		String customerStatement = customer.statement();

		assertEquals(getCustomerStatement(rentals), customerStatement);
	}

	@Test
	public void testStatement05Renting1ChildrenForMoreThan3Days() {
		List<Rental> rentals = Arrays
				.asList(new Rental(new Movie(getRandomMovieTitle(), Movie.CHILDREN), getRandomRentalDaysRented(3)));
		for (Rental rental : rentals) {
			customer.addRental(rental);
		}

		String customerStatement = customer.statement();

		assertEquals(getCustomerStatement(rentals), customerStatement);
	}

	@Test
	public void testStatement11RentingAllTypesForNoMoreThan2Days() {
		List<Rental> rentals = Arrays.asList(
				new Rental(new Movie(getRandomMovieTitle(), Movie.REGULAR), getRandomRentalDaysRented(-2)),
				new Rental(new Movie(getRandomMovieTitle(), Movie.NEW_RELEASE), getRandomRentalDaysRented(-2)),
				new Rental(new Movie(getRandomMovieTitle(), Movie.CHILDREN), getRandomRentalDaysRented(-2)));
		for (Rental rental : rentals) {
			customer.addRental(rental);
		}

		String customerStatement = customer.statement();

		assertEquals(getCustomerStatement(rentals), customerStatement);
	}

	@Test
	public void testStatement12RentingAllTypesForMoreThan2Days() {
		List<Rental> rentals = Arrays.asList(
				new Rental(new Movie(getRandomMovieTitle(), Movie.REGULAR), getRandomRentalDaysRented(2)),
				new Rental(new Movie(getRandomMovieTitle(), Movie.NEW_RELEASE), getRandomRentalDaysRented(2)),
				new Rental(new Movie(getRandomMovieTitle(), Movie.CHILDREN), getRandomRentalDaysRented(2)));
		for (Rental rental : rentals) {
			customer.addRental(rental);
		}

		String customerStatement = customer.statement();

		assertEquals(getCustomerStatement(rentals), customerStatement);
	}

	@Test
	public void testStatement13RentingAllTypes() {
		List<Rental> rentals = Arrays.asList(
				new Rental(new Movie(getRandomMovieTitle(), Movie.REGULAR), getRandomRentalDaysRented()),
				new Rental(new Movie(getRandomMovieTitle(), Movie.NEW_RELEASE), getRandomRentalDaysRented()),
				new Rental(new Movie(getRandomMovieTitle(), Movie.CHILDREN), getRandomRentalDaysRented()));
		for (Rental rental : rentals) {
			customer.addRental(rental);
		}

		String customerStatement = customer.statement();

		assertEquals(getCustomerStatement(rentals), customerStatement);
	}

	@Test
	public void testStatement21RentingRandomly() {
		List<Rental> rentals = new ArrayList<>();
		int rentalCount = new Random().nextInt(20) + 1;
		for (int i = 0; i < rentalCount; i++) {
			rentals.add(new Rental(new Movie(getRandomMovieTitle(), getRandomMoviePriceCode()),
					getRandomRentalDaysRented()));
		}

		for (Rental rental : rentals) {
			customer.addRental(rental);
		}

		String customerStatement = customer.statement();

		assertEquals(getCustomerStatement(rentals), customerStatement);
	}

	// -------------------------------------------------------------------------

	private String getCustomerStatement(List<Rental> rentals) {
		StringBuilder result = new StringBuilder();

		result.append("Rental Record for " + customer.getName());
		for (Rental rental : rentals) {
			result.append("\n\t" + rental.getMovie().getTitle() + "\t" + getAmount(rental));
		}
		result.append("\nAmount Owed: " + getTotalAmount(rentals));
		result.append("\nFrequent Renter Points Earned: " + getTotalFrequentRenterPoints(rentals));

		return result.toString();
	}

	private static String getRandomMovieTitle() {
		return RandomStringUtils.randomAlphabetic(20);
	}

	private static int getRandomMoviePriceCode() {
		return MOVIE_PRICE_CODES[new Random().nextInt(MOVIE_PRICE_CODES.length)];
	}

	private static int getRandomRentalDaysRented() {
		return getRandomRentalDaysRented(0);
	}

	private static int getRandomRentalDaysRented(int boundaryDays) {
		int daysRange;
		int daysDifference;

		if (boundaryDays < 0) {
			daysRange = Math.abs(boundaryDays);
			daysDifference = 1;
		} else {
			daysRange = DEFAULT_MAX_RENTAL_DAYS_RENTED - boundaryDays;
			daysDifference = boundaryDays + 1;
		}

		return new Random().nextInt(daysRange) + daysDifference;
	}

	private static double getAmount(Rental rental) {
		double result = 0;

		switch (rental.getMovie().getPriceCode()) {
		case Movie.REGULAR:
			result += 2;
			if (rental.getDaysRented() > 2) {
				result += (rental.getDaysRented() - 2) * 1.5;
			}
			break;

		case Movie.NEW_RELEASE:
			result += rental.getDaysRented() * 3;
			break;

		case Movie.CHILDREN:
			result += 1.5;
			if (rental.getDaysRented() > 3) {
				result += (rental.getDaysRented() - 3) * 1.5;
			}
			break;
		}

		return result;
	}

	private static double getTotalAmount(List<Rental> rentals) {
		double result = 0;

		for (Rental rental : rentals) {
			result += getAmount(rental);
		}

		return result;
	}

	private static int getFrequentRenterPoints(Rental rental) {
		int result = 0;

		result++;
		if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE && rental.getDaysRented() > 1) {
			result++;
		}

		return result;
	}

	private static int getTotalFrequentRenterPoints(List<Rental> rentals) {
		int result = 0;

		for (Rental rental : rentals) {
			result += getFrequentRenterPoints(rental);
		}

		return result;
	}

}
