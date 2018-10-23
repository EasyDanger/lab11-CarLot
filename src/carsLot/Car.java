package carsLot;

public class Car {
	protected String make;
	protected String model;
	protected int year;
	protected double price;

	public Car() {
		this.make = "Ford";
		this.model = "Model T";
		this.year = 1900;
		this.price = 10.00;
	}

	public Car(String make, String model, int year, double price) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.price = price;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}

	public double getPrice() {
		return price;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setprice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("%-12s%-12s%-12s$%-12s", make, model, year, price);
	}
}
