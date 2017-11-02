class Controlleredit
{
	private static int cumulativeDose = 0;
	
	// Input device definition
	
	
	// Output device definition
	private static int dose = 0;

// Dose computation variables
	private static int reading0 = 0;
	private static int reading1 = 0;
	private static int reading2 = 0;
	private static int remainingInsulin = 100;
	private static int reservoirCapacity = 100;
	private static int maxDailyDose = 25;
	private static int maxSingleDose = 4;
	private static int minDose = 1;
	private static int safeMin = 6;
	private static int safeMax = 14;
	private static int compDose = 0;
	

	reading0 = safeMin;
	reading1 = safeMax;

/* Before read in, a safety check is performed to determine that the system mode is set to Auto */

 if (remainingInsulin >= maxSingleDose && cumulativeDose < maxDailyDose) 
		{
			//retrieve current blood sugar level
						//reading2 = Sensor.getReading();

						/* Sugar level low */
						
						if (reading2 < safeMin)
						{
							compDose = 0;
						}
						
						/* Sugar level OK */
						
						else if (reading2 >= safeMin && reading2 <= safeMax)
						{
							// If sugar level is stable or falling
							if (reading2 <= reading1)
							{
								compDose = 0;
							}
							// If sugar level increasing
							else
							{
								// If rate of increase is falling
								if ((reading2 - reading1) < (reading1 - reading0))
								{
									compDose = 0;
								}
								// If rate of increase is increasing
								else if ((reading2 - reading1) >= (reading1 - reading0))
								{
									// If dose is rounded to zero, deliver the min dose
									if ((reading2 - reading1) / 4 == 0)
									{
										//set the amount to deliver to the min dose
										compDose = minDose;
									}
									else if ((reading2 - reading1) / 4 > 0)
									{
										//Set the amount to deliver
										compDose = (reading2 - reading1) / 4;
									}
								}
							}
						}
						
						/* Sugar level High */
						else if (reading2 > safeMax)
						{
							// If Sugar level increasing
							if (reading2 > reading1)
							{
								// If dose is rounded to zero, deliver the min dose
								if ((reading2 - reading1) / 4 == 0)
								{
									compDose = minDose;
								}
								else if ((reading2 - reading1) / 4 > 0)
								{
									//Set the amount to deliver
									compDose = (reading2 - reading1) / 4;
								}
							}
							// If the Sugar level is stable
							else if (reading2 == reading1)
							{
								compDose = minDose;
							}
							// If the Sugar level is falling
							else if (reading2 < reading1)
							{
								// If rate of decrease increasing
								if ((reading2 - reading1) <= (reading1 - reading0))
								{
									compDose = 0;
								}
								// If rate of decrease decreasing
								else
								{
									compDose = minDose;
								}
							}
						}
						
						/*  Check that dose computed is allowed to be administered/safety */

						//If dose calculated is 0, don't deliver any insulin
						if (compDose == 0)
						{
							dose = 0;
						}
						//If dose exceeds the remaining Insulin left
						else if (dose > remainingInsulin)
						{
							//Alert the user and set status to Warning
						}
						else
						{
							//If max daily dose will be exceeded by the dose calculated
							if ((compDose + cumulativeDose) > maxDailyDose)
							{
								//Alert the user and set status to Warning
								dose = maxDailyDose - cumulativeDose;
								//InsulinPump.deliverInsulin(dose);
							}
							//Normal situation
							else if ((compDose + cumulativeDose) < maxDailyDose)
							{
								if (compDose <= maxSingleDose)
								{
									dose = compDose;
									//InsulinPump.deliverInsulin(dose);
								}
								//If the single dose computed is too high
								else
								{
									dose = maxSingleDose;
									//InsulinPump.deliverInsulin(dose);
								}
							}
						}
					
					//Increment and decrement the necessary counters
						remainingInsulin = remainingInsulin - dose;
						cumulativeDose = cumulativeDose + dose;

					//Adjust the reading values to accommodate for the new reading
						reading1 = reading2;
						reading0 = reading1;		
		}
	}
}
		
				
