package org.usfirst.frc.team4041.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Speaker extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static Speaker instance;
	private static final Relay spike = new Relay(3,Relay.Direction.kBoth);
	public static Speaker getInstance() {

		if (instance == null) {
			instance = new Speaker();
		}
		return instance;
	}
	
	public void initialize() {

	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}
	
	public void on() { 
		
		
			spike.set(Relay.Value.kForward);
			//Timer.delay(0.05);
			//spike.set(Relay.Value.kOff);
			//Timer.delay(0.05);
		
	}


	public void stop(){
		spike.set(Relay.Value.kOff);
	}

	
}
