//Dillan Ellis and Madeleine Godwin



//plan on finishing InsulinLeft method tomorrow morning and helping with updating for lauras buttons

import java.util.Random;
import java.util.Scanner;
import java.util.Timer;


public class Testcontroller {

	public static void main(String[] args) 
	{ //should be in main here just for testing
		runPump();
		
		Timer timer = new Timer();
		timer.schedule(new Timers(), 0 , 100000); //300000
		
	}

	static int dose=0;
	
	static int reading0 = 9;
	static int reading1 = 3;
	static int reading2 = 7;
	
	static int safeMin=60;
	static int safeMax=120;
	static int minDose = 10;
	static int maxSingleDose=20;
	static int vialAmount=50;
	static int updatedVialAmount;
	
	
	static int compDose=0;
	
	public static void runPump()
	{

		reading0 = 120; //for testing
		reading1 = 100; //for testing
		
		
while(true){
		System.out.println("check Bs?");
		Scanner s=new Scanner(System.in);
		String checking=s.nextLine();
		
		if(checking.equals("yes"))
		{ // random number generated every time for reading 2
			Random rand = new Random(); 
			int reading2 = rand.nextInt(350) + 60; 
			System.out.println("Blood Sugar: "+ reading2);
			
			
		/*	System.out.println("Enter BS");
		Scanner s1=new Scanner(System.in);
		int reading2=s1.nextInt();
		//System.out.println(reading2);
		
		/*Sugar reading is low*/
		if (reading2 < safeMin)
		{
			compDose = 0;
			System.out.println(dose+" Sugar is too low");
		}
		
		/*Sugar is within safe range*/
		else if (reading2 >= safeMin && reading2 <= safeMax)
		{
			// If sugar level is stable or falling
			if (reading2 <= reading1)
			{
				compDose = 0;
				System.out.println("Sugar is stable or falling");
			}
			// If sugar level increasing
			else
			{
				// If rate of increase is falling
				if ((reading2 - reading1) < (reading1 - reading0))
				{
					compDose = 0;
					System.out.print("rate of increase is falling");
				}
				// If rate of increase is increasing
				else if ((reading2 - reading1) >= (reading1 - reading0))
				{ System.out.print("rate of increase is increasing");
					// If dose is rounded to zero, deliver the min dose
					if ((reading2 - reading1) / 40 == 0)
					{
						//set the amount to deliver to the min dose
						compDose = minDose;
					}
					else if ((reading2 - reading1) / 40 > 0)
					{
						//Set the amount to deliver
						compDose = (reading2 - reading1) / 40;
					}
				}
			}
		}
		
		/*Sugar is High*/
		else if (reading2 > safeMax)
		{
			System.out.println("Sugar is High");
			// If Sugar level increasing
			if (reading2 > reading1)
			{
				System.out.println("Sugar is High and increasing");
				// If dose is rounded to zero, deliver the min dose
				if ((reading2 - reading1) / 40 == 0)
				{
					compDose = minDose;
				}
				else if ((reading2 - reading1) / 40 > 0)
				{
					//Set the amount to deliver
					compDose = (reading2 - reading1) / 40;
				}
			}
			// If the Sugar level is stable
			else if (reading2 == reading1)
			{
				compDose = minDose;
				System.out.println("Sugar is Stable");
			}
			// If the Sugar level is falling
			else if (reading2 < reading1)
			{
				System.out.println("Sugar level is falling");
				// If rate of decrease increasing
				if ((reading2 - reading1) <= (reading1 - reading0))
				{
					System.out.println("rate of decrease is increasing");
					compDose = 0;
				}
				// If rate of decrease decreasing
				else
				{
					System.out.println("rate of decrease is decreasing");
					compDose = minDose;
				}
			}
		}
		
		if (compDose <= maxSingleDose)
		{
			dose = compDose;
			System.out.println("Suggested Dose: "+ dose+"mL");
			InsulinPump.deliverInsulin(dose);
		}
		//If the single dose computed is too high
		else
		{
			dose = maxSingleDose;
			System.out.println(dose);
			System.out.println("single dose is too high");
			//InsulinPump.deliverInsulin(dose);
		}
	
		if (dose > 0)
		{
			//Display to the user the dose delivered
			System.out.println("Dose Delivered after check: " + dose+"mL");
		}

		//Adjust the reading values to accomodate for the new reading
		
//		System.out.println("reading0 before accomadation: "+reading0);
//		System.out.println("reading1 before accomadation: "+reading1);
//		System.out.println("reading2 before accomadation: "+reading2);
		
		reading0=reading1;
		reading1=reading2;
		reading2=0;

//		System.out.println("after reading0: "+reading0);
//		System.out.println("after reading1: "+reading1);
//		System.out.println("after reading2: "+reading2);

		InsulinLeft();// found at bottom ad working
		Battery.Level(); // good
		} 
		
		else{
			System.out.println("ended. or input error");
			break;
			}
		}
	
	}
	
	private static void InsulinLeft() // Insulin left over in the reservour/vial.
	{
		System.out.println("Vial total =" + vialAmount);
		updatedVialAmount=(vialAmount-dose);
		vialAmount=updatedVialAmount;
		
		System.out.println("Insulin leftover: " + vialAmount + "mL");
		
		if (vialAmount <=0)
		{
			System.out.println("WARNING: No insulin vial needed");
		}
		else if (vialAmount > 0)
		{
			System.out.println( "Vial State: Good");
			
		}
		
	}


}





