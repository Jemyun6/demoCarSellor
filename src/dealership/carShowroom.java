package dealership;

import java.util.ArrayList;

public class carShowroom {
	private int capacity;
	boolean flagBuyer = true;
	boolean flagSeller = true;// related to the thread is waiting or not
	ArrayList<car> car = new ArrayList<car>() {
	};

	public carShowroom(int capacity) {
		super();// create a carShowroom will have 3 cars in it ,
		// and you should set a value of capacity for it
		this.capacity = capacity;
		car.add(new car());
		car.add(new car());
		car.add(new car());
	}

	public Boolean isEmpty() {// check the carShowroom is Empty or not
		if (car.isEmpty()) {
			return true;
		}
		return false;
	}

	public Boolean isFull() {// check the carShowroom is Full or not
		if (car.size() < capacity) {
			return false;// the car.size() can not bigger than the capacity
		}
		return true;
	}

	public synchronized void addCar() {// when adding a car,it will print out its information
		car c = new car();
		System.out.print(c.toString() + " to the showroom");
		car.add(c);// ArrayList car will add this car
		System.out.println();
	}

	public synchronized void takeCar() {// removing a car

		System.out.println(car.get(0).toString());
		car.remove(0);// remove the oldest car from ArrayList car

	}

}
