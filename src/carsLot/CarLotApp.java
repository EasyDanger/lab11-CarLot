package carsLot;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CarLotApp {
	private static List<Car> cars = new ArrayList<>();
	static boolean finished = false;
	static Scanner read = new Scanner(System.in);

	public static void main(String[] args) {

		UsedCar baja = new UsedCar("Subaru", "Baja", 2006, 5500, 26657.34);
		cars.add(baja);
		UsedCar vibe = new UsedCar("Pontiac", "Vibe", 2004, 4000.00, 100500.24);
		cars.add(vibe);
		UsedCar leBaron = new UsedCar("Chrysler", "LeBaron", 1991, 500.00, 298483.97);
		cars.add(leBaron);
		Car tesla = new Car("Tesla", "Model S", 2017, 65200.00);
		cars.add(tesla);
		Car f150 = new Car("Ford", "F-150", 2019, 28155.00);
		cars.add(f150);
		Car ghibli = new Car("Mazerati", "Ghibli", 2018, 74980.00);
		cars.add(ghibli);
		Car blank = new Car();
		cars.add(blank);

		System.out.println(
				"Welcome to the Danger Motors Employee Database Administrative Console Operations Application!");

		do {
			finished = false;
			System.out.println("\nSelect an option:\n");
			System.out.println("1. List of Available Cars (\"list\")");
			System.out.println("2. Add a Car (\"add\")");
			System.out.println("3. Remove a Car (\"remove\")");
			System.out.println("4. Look up a car by index humber (\"look\")");
			System.out.println("5. Replace a car at a specific index number (\"replace\")");
			System.out.println("6. Exit this application (\"quit\")");

			String option = read.nextLine();

			switch (option) {
			case "list":
			case "1":
				list();
				break;
			case "add":
			case "2":
				System.out.println("\n\nIs the car you want to add new or used? {\"new\"/\"used\")");
				option = read.nextLine();
				switch (option) {
				case "new":
					addCar();
					break;
				case "used":
					addUsed();
					break;
				default:
					System.out.println("Sorry, that input wasn't valid. back to the main menu.");
					break;
				} 
				break;
			case "remove":
			case "3":
				list();
				remove();
				break;
			case "look":
			case "4":
				doSelect();
				break;
			case "quit":
			case "6":
				finished = true;
				break;
			default:
				System.out.println("Sorry, but that choice isn't valid.");
				break;
			}
		} while (!finished);
	}

	private static void remove() {

			int drop = (Vali.getInt(read, "Which car would you like to remove?\nEnter its number.", 0, cars.size())) -1;
		System.out.println("Are you sure you want to remove that " + cars.get(drop).getMake() + " from the list of cars?");
		if (Vali.checkYes(read.nextLine())){
			cars.remove(drop);
		}
		finished = false;
	}

	private static void addUsed() {
		System.out.println("Please enter the make of the car:");
		String make = read.nextLine();
		System.out.println("\nNow enter the model of the car:");
		String model = read.nextLine();
		System.out.println("\nNext, we need the year the car was made:");
		int year = 0;
		do {
			finished = false;

			try {
				year = read.nextInt();
				read.nextLine();
				if (year > 9999 || year < 1000) {
					throw new InputMismatchException();
				} else {
					finished = true;
				}
			} catch (InputMismatchException ex) {
				System.out.println("Sorry, that's not a valid year. Please enter a 4 digit model year.");
			}
		} while (!finished);
		System.out.println("\nNow, put in the price of the car:");
		double price = 0;
		do {
			finished = false;
			try {
				price = read.nextDouble();
				read.nextLine();
				finished = true;
			} catch (InputMismatchException ex) {
				System.out.println("Sorry, that's not a valid price. Please enter a number without a \"$\".");
				read.nextLine();
			}
		} while (!finished);
		System.out.println("Finally, enter the total mileage of the car:");
		double miles = 0;
		do {
			finished = false;
			try {
				miles = read.nextDouble();
				read.nextLine();
				finished = true;
			} catch (InputMismatchException ex) {
				System.out.println("Sorry, that's not a valid number of miles. Please enter a number.");
				read.nextLine();
			}
		} while (!finished);
		
		System.out.println("Are you sure you want to create a " + year + " " + make +" "+ model +" for $"+price+" with " + miles + "? (y/n)"); 
		if (Vali.checkYes(read.nextLine())) {
			cars.add(new UsedCar(make, model, year, price, miles));
		}else {
			System.out.println("Do you still want to add another car? (y/n)");
			if (Vali.checkYes(read.nextLine())) {
				addUsed();
			}
		}
			finished = false;
		
	}

	private static void addCar() {
		System.out.println("Please enter the make of the car:");
		String make = read.nextLine();
		System.out.println("\nNow enter the model of the car:");
		String model = read.nextLine();
		System.out.println("\nNext, we need the year the car was made:");
		int year = 0;
		do {
			finished = false;

			try {
				year = read.nextInt();
				read.nextLine();
				if (year > 9999 || year < 1000) {
					throw new InputMismatchException();
				} else {
					finished = true;
				}
			} catch (InputMismatchException ex) {
				System.out.println("Sorry, that's not a valid year. Please enter a 4 digit model year.");
			}
		} while (!finished);
		System.out.println("\nFinally, put in the price of the car:");
		double price = 0;
		do {
			finished = false;
			try {
				price = read.nextDouble();
				read.nextLine();
				finished = true;
			} catch (InputMismatchException ex) {
				System.out.println("Sorry, that's not a valid price. Please enter a number without a \"$\".");
				read.nextLine();
			}
		} while (!finished);
		System.out.println("Are you sure you want to create a " + year + " " + make +" "+ model +" for $"+price+"? (y/n)"); 
		if (Vali.checkYes(read.nextLine())) {
			cars.add(new Car(make, model, year, price));
		}else {
			System.out.println("Do you still want to add a new car? (y/n)");
			if (Vali.checkYes(read.nextLine())) {
				addCar();
			}
		}
			finished = false;
		
	}

	private static void doSelect() {
		System.out.println("Pick a car.");
		int make = read.nextInt();
		read.nextLine();
		System.out.println(cars.get(make - 1));

	}

	private static void list() {
		System.out.println("\nList of all available cars");
		System.out.println("==========================");
		int i = 0;
		for (Car car : cars) {
			i++;
			System.out.println(i + ". " + car);
		}

	}

}
