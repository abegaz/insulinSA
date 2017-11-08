import java.util.Random;

public class Battery 
{

// random battery level numbers between 49 and 90
	public static void Level()
	{
	Random rand = new Random(); 
	 int pickedNumber = rand.nextInt(99) + 1; 
	 //System.out.println("Battery Level: "+ pickedNumber +"%");
	if (pickedNumber <=20)
	{
		System.out.println("WARNING: Battery Level Low:" +pickedNumber +"%");
	}
	else if (pickedNumber > 20) 
	{
		System.out.println("Battery Level good:" + pickedNumber+ "%");
	}
	}
	
}