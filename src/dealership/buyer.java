package dealership;

import java.util.concurrent.atomic.AtomicInteger;

public class buyer implements Runnable {
	private int ID;
	private carShowroom carShowroom;
	private static AtomicInteger buyer = new AtomicInteger(0);// calculate the buyer
	private static AtomicInteger purchase = new AtomicInteger(0);// calculate the successful purchase

	public void uID() {
		ID = buyer.get();
	}

	public buyer(dealership.carShowroom carShowroom) {
		super();
		this.carShowroom = carShowroom;
	}

	@Override
	public void run() {

		synchronized (carShowroom) {
			if (this.ID == 0) {// if the customer have no ID,will create a unique ID for the customer
				this.getBuyer();
				this.uID();
				System.out.println("new Buyer #" + this.ID + " just appeared");

			}
			if (!carShowroom.isEmpty()) {
				try {// buyer can buy a car when the carShowroom is not empty
					purchase.getAndIncrement();// calculate the successful purchase
					System.out.print("   Buyer #" + this.ID + " bought a ");
					carShowroom.takeCar();// calling the takeCar() method from carShowroom
					if (!carShowroom.flagSeller) {
						notify();// when the carShowroom is full,flagSeller will become false,and seller is
									// waiting,
						// once buyer had bought a car, seller can sell the car and notify the seller
						// thread
					}
					this.getPurchase();

				} catch (Exception e) {
					// TODO: handle exception
				}

			}

			if (carShowroom.isEmpty()) {
				try {
					System.out.println("Buyer #" + this.ID + " wants to buy a car,but the carshowroom is empty.");
					wait();// no cars in car showroom,buyer should wait,and flagBuyer become false
					carShowroom.flagBuyer = false;
				} catch (Exception e) {

				}

			}

		}
	}

	public int getBuyer() {// calculate the buyer,so that every buyer will have a unique ID
		buyer.getAndIncrement();
		return buyer.get();

	}

	public int getPurchase() {
		return purchase.get();
	}

}
