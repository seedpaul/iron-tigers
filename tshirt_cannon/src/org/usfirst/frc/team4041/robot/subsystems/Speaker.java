package org.usfirst.frc.team4041.robot.subsystems;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Speaker extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static Speaker instance;
	private static final Relay spike = new Relay(RobotMap.spike_relay_speaker,Relay.Direction.kBoth);
	public static Speaker getInstance() {

		if (instance == null) {
			instance = new Speaker();
		}
		return instance;
	}
	
	public void initialize() {}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}
	
	public void on() { 
		spike.set(Relay.Value.kForward);
	}

	public void stop(){
		spike.set(Relay.Value.kOff);
	}

}

