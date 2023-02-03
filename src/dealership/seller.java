package dealership;

import java.util.concurrent.atomic.AtomicInteger;

public class seller implements Runnable {
	private int ID;
	private carShowroom carShowroom;
	private static AtomicInteger seller = new AtomicInteger(0);// calculate the seller
	private static AtomicInteger sold = new AtomicInteger(0);// calculate the sold

	public seller(dealership.carShowroom carShowroom) {
		super();
		this.carShowroom = carShowroom;
	}

	@Override
	public void run() {
		//
		synchronized (carShowroom) {

			if (this.ID == 0) {// if the seller have no ID,will create a unique ID for the seller
				this.getSeller();
				this.uID();
				System.out.println("new Seller #" + this.ID + " just appeared");

			}

			if (!carShowroom.isFull()) {
				try {// if the carShowroom is not full,seller can sell the car ,and the message will
						// be print
					sold.getAndIncrement();
					System.out.print("   Seller #" + this.ID + " sold a ");
					carShowroom.addCar();// carShowroom adds a new car
					if (!carShowroom.flagBuyer) {
						notify();// if carShowroom is empty,flagBuyer will become false ,buyer thread is waiting
						// once carShowroom has add a new car ,buyer can buy,the buyer thread will be
						// notified
					}
					this.getSold();

				} catch (Exception e) {
					// TODO: handle exception
				}

			}
			if (carShowroom.isFull()) {
				try {
					System.out.println("Seller #" + this.ID + " wants to sell a car,but the showroom is full");
					wait();// car showroom is full,the seller Thread should wait
					carShowroom.flagSeller = false;// and flagSeller becomes false

				} catch (Exception e) {

				}
			}

		}
	}

	public void uID() {
		ID = seller.get();
	}

	public int getSeller() {
		seller.getAndIncrement();// calculate the seller ,so each seller has a unique id
		return seller.get();

	}

	public int getSold() {

		return sold.get();

	}

}
