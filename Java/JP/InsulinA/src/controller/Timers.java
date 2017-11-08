package controller;

import java.util.TimerTask;

// every minute for testing purposes.

public class Timers extends TimerTask{	

	//this method performs the task
	public void run () {
	// need to automatically input yes in the test controller for check bs.
	Testcontroller.runPump();
	}
}