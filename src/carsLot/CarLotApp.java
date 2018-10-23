package carsLot;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CarLotApp {
	// cars list is needed by the whole app.
	private static List<Car> cars = new ArrayList<>();
	// finished is used for looping.
	static boolean finished = false;
	// A scanner!
	static Scanner read = new Scanner(System.in);

	public static void main(String[] args) {
		// these Cars very nearly got moved up unnecessarily to be static class
		// variables. I realized that i never actually had to call on any of these
		// variables by name, only from their index in the list. Thus, they could stay
		// where they were. Anyway, these give my list something to work with, so the
		// exercise doesn't have to start off populating the list every time we want to
		// test.
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
		// initial greeting is outside the loop.
		System.out.println(
				"Welcome to the Danger Motors Employee Database Administrative Console Operations Application!");
		// Do loop allows menu to loop. I considered a try catch here as well, however,
		// this is a small enough program that I think it's okay to only accept Strings.
		// I assume this would lead ot more resource consumption if it were a regular
		// thing in a different app, so I later switch to try catches and eventually
		// (from my timeline POV, not the order of the app) to using my validation
		// class.
		do {
			// It doesn't matter false is initialized, so long as it's before this loop
			// needs to check it. It's easier to understand at this location, though.
			finished = false;
			// menu options can be accessed by number or keyword. More practice using the
			// escape slash "\" with the quotes.
			System.out.println("\nSelect an option:\n");
			System.out.println("1. List of Available Cars (\"list\")");
			System.out.println("2. Add a Car (\"add\")");
			System.out.println("3. Remove a Car (\"remove\")");
			System.out.println("4. Look up a car by index humber (\"look\")");
			System.out.println("5. Replace a car at a specific index number (\"replace\")");
			System.out.println("6. Exit this application (\"quit\")");
			// there are a lot of specific options, so I'm allowed to use a switch again!
			String option = read.nextLine();
			switch (option) {
			// cases take advantage of the break function to allow multiple controls to lead
			// to the same option.
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
					addCar(cars.size());
					break;
				case "used":
					addUsed(cars.size());
					break;
				default:
					System.out.println("Sorry, that input wasn't valid. Back to the main menu.");
					break;
				}
				break;
			case "remove":
			case "3":
				// I figure the user needs to know what cars are available in order to drop one.
				// Maybe they remember, but why make it hard?
				list();
				remove();
				break;
			case "look":
			case "4":
				doSelect();
				break;
			case "replace":
			case "5":
				// I'm quite proud of this. I started not to add the replace option below,
				// because it seemed to tedious. Then I realized I still had access to the
				// getInt function in the validator class, and that allows me to easily and
				// quickly verify integers between a certain range. Combined with the fact that
				// I can always functionally add items to the end of a list by serving the
				// list's size as the index argument for the add() method, and I could add three
				// words to my addCar/Used methods to allow them to accept Cars onto the list at
				// specific indices. It took longer to type this explanation than it took to do!
				list();
				int place = (Vali.getInt(read, "Which number car do you want to replace?")) - 1;
				System.out.println("\n\nIs the car you want to add new or used? {\"new\"/\"used\")");
				option = read.nextLine();
				switch (option) {
				case "new":
					addCar(place);
					break;
				case "used":
					addUsed(place);
					break;
				default:
					System.out.println("Sorry, that input wasn't valid. Back to the main menu.");
					break;
				}
				break;
			// Our exit condition.
			case "quit":
			case "6":
				finished = true;
				break;
			default:
				System.out.println("Sorry, but that choice isn't valid.");
				break;
			}
		} while (!finished);

		System.out.println("Thanks for using the DMEDACOA. Goodbye.");
	}

//Method to remove cars from cars.
	private static void remove() {
		// First time I used the getInt method. It must be minus one because of how
		// indices work. This version allows us to verify the range.
		int drop = (Vali.getInt(read, "Which car would you like to remove?\nEnter its number.", 0, cars.size())) - 1;
		// Allows user to verify their choice. I tried to give this option before every
		// change.
		System.out.println(
				"Are you sure you want to remove that " + cars.get(drop).getMake() + " from the list of cars?");
		// I added my checkYes method to the Vali class. I hope to further develop it,
		// but it's helpful enough for now, even if it can only accept "y" and "yes".
		if (Vali.checkYes(read.nextLine())) {
			cars.remove(drop);
		}
		// I started putting this in places even if I didn't need to, for safety. This
		// finished is doing nothing, but I wanted to make sure in some places that my
		// menu loop would continue to loop.
		finished = false;
	}

//Method to add used cars.
	private static void addUsed(int place) {
		// We need inputs to enter into the constructor. I could have probably used
		// ReGex here to allow them to put it all on one line. But that would be
		// unwieldy, I think. Also, tedious.
		System.out.println("Please enter the make of the car:");
		String make = read.nextLine();
		System.out.println("\nNow enter the model of the car:");
		String model = read.nextLine();
		System.out.println("\nNext, we need the year the car was made:");
		int year = 0;
		// These aren't strings, and thus need more validation. It was after this sort
		// of verification in the addCar method that i realized I already had access to
		// a cheap int validator. This method is largely a copy of the addCar method,
		// but with mileage stuff added. Mileage and price validation were just copied
		// and adapted from the year validation.
		do {
			// Just making sure again.
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
		System.out.println("Are you sure you want to create a " + year + " " + make + " " + model + " for $" + price
				+ " with " + miles + "? (y/n)");
		if (Vali.checkYes(read.nextLine())) {
			cars.add(place, new UsedCar(make, model, year, price, miles));
		} else {
			System.out.println("Do you still want to add another car? (y/n)");
			if (Vali.checkYes(read.nextLine())) {
				addUsed(place);
			}
		}
		// this finished is actually useful, since this method does screw around with
		// this boolean and i need it false for the loop back in the main method.
		finished = false;
	}

//Method to add car. Everything here is the same as in usedCar, minus mileage stuff. There is probably a better way to "extend" a method like that besides just copy/paste.
	private static void addCar(int place) {
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
		System.out.println(
				"Are you sure you want to create a " + year + " " + make + " " + model + " for $" + price + "? (y/n)");
		if (Vali.checkYes(read.nextLine())) {
			cars.add(place, new Car(make, model, year, price));
		} else {
			System.out.println("Do you still want to add a new car? (y/n)");
			if (Vali.checkYes(read.nextLine())) {
				addCar(place);
			}
		}
		finished = false;
	}

//Method to peek at a single car.
	private static void doSelect() {
		System.out.println("Pick a car.");
		int make = read.nextInt();
		read.nextLine();
		System.out.println(cars.get(make - 1));
	}

//Method to list cars.
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
