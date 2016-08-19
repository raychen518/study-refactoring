package com.raychen518.study.refactoring.thefirstcase.after;

public class Movie {

	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	public static final int CHILDREN = 2;

	private static final String MESSAGE_INVALID_MOVIE_PRICE_CODE = "Invalid movie price code: ";

	private String title;
	private Price price;

	public Movie(String title, int priceCode) {
		this.title = title;
		setPriceCode(priceCode);
	}

	public String getTitle() {
		return title;
	}

	public int getPriceCode() {
		return price.getPriceCode();
	}

	public void setPriceCode(int priceCode) {
		switch (priceCode) {
		case REGULAR:
			price = new RegularPrice();
			break;

		case NEW_RELEASE:
			price = new NewReleasePrice();
			break;

		case CHILDREN:
			price = new ChildrenPrice();
			break;

		default:
			throw new IllegalArgumentException(MESSAGE_INVALID_MOVIE_PRICE_CODE + priceCode);
		}
	}

	double getCharge(int daysRented) {
		return price.getCharge(daysRented);
	}

	int getFrequentRenterPoints(int daysRented) {
		return price.getFrequentRenterPoints(daysRented);
	}

}
