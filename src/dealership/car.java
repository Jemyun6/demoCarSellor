package dealership;

import java.text.Format;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class car {
	private String registration;
	private int sale;
	private String colour;
	public void register() {// generate  format"00-G-0000" randomly
		int a=(int) (Math.random()*90+10);
		Random random= new Random();
		char c=(char)(random.nextInt(25)+65);
		int b=(int) (Math.random()*9000+1000);
		registration=a+"-"+c+"-"+b;
		
	}
	public  void sale() {
		sale=(int) (Math.random()*(19000)+1000);
		//Randomly generate a integer from[1000,20000]
		
	}
	public  void color() {//select one color from this arraylist
		ArrayList<String> list =new ArrayList<>();
		list.add("RED");
		list.add("BLACK");
		list.add("WHITE");
		list.add("GREY");
		list.add("BLUE");
		Collections.shuffle(list);//shuffling
		Random random= new Random();//generate a color randomly
		int n= random.nextInt(list.size());
		colour=list.get(n);

	}
	public void generate() {//generate this car's registration.colour and its price 
		this.register();
		this.color();
		this.sale();
	}

	public car() {//  constructor,create a new car
		super();
		this.registration = registration;
		this.sale = sale;
		this.colour = colour;
		this.generate();//generate this car's attributes
		
		System.out.println();
	}	
		
	 
	 @Override
	public String toString() {//override toString
		return this.colour+" car with registration " +this.registration +" worth ву " + this.sale;
	}
	 
 

}
