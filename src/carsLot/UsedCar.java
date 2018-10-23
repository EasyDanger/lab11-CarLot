package carsLot;

public class UsedCar extends Car{
	protected double miles;

	public UsedCar() {
		super();
		this.miles = 9999999.99;
		// TODO Auto-generated constructor stub
	}

	public UsedCar(String make, String model, int year, double price, double miles) {
		super(make, model, year, price);
		this.miles = miles;
		// TODO Auto-generated constructor stub
	}

	public double getMiles() {
		return miles;
	}

	public void setMiles(double miles) {
		this.miles = miles;
	}
	
	@Override
	public String toString() {
		return String.format("%-12s%-12s%-12s%-12s%-12s", make, model, year, "$" + price,"(Used) " + miles + " miles");
	}
	

}
