package dealership;

import java.util.*;

public class carDealerTest extends car {
	public static int day = 0;

	public static void main(String[] args) throws Exception {
		carShowroom carShowroom1 = new carShowroom(10);
		// create a instance :carShowroom1, capacity is 10
		while (day < 31) {// simulate of days(31)

			int m = (int) (Math.random() * 3);// each day will have a random number of Sellers(0-3)
			seller[] seller = new seller[m + 1];// create an array for sellers
			Thread[] Thread2 = new Thread[m + 1];
			for (int i = 0; i < m - 1; i++) {
				seller[i] = new seller(carShowroom1);// each seller share the same carShowroom
				Thread2[i] = new Thread(seller[i]);// each instance of seller will run as a separate thread
				Thread2[i].start();// each new seller a new thread be created and started
			}

			int n = (int) (Math.random() * 3);// each day will have a random number of Buyers(0-3)
			buyer[] buyer = new buyer[n + 1];// an array for buyers
			Thread[] thread1 = new Thread[n + 1];
			for (int i = 0; i < n - 1; i++) {
				buyer[i] = new buyer(carShowroom1);// all buyers share the instance carShowroom1
				thread1[i] = new Thread(buyer[i]);// each instance of buyer will run as a separate thread
				thread1[i].start();// each buyer thread should be started

			}
			day++;
			System.out// announcing the number of cars available each day
					.println("Day " + day + " beginning, we have " + carShowroom1.car.size() + "cars in car showroom");

			Thread t = new Thread();
			t.sleep(1200);// a delay at the end of each day
		}

	}

}
