package org.usfirst.frc.team4041.robot.subsystems;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Angler extends Subsystem {
	
	private static Angler instance;

	static final Talon talon = new Talon(RobotMap.cannonTalon);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Angler() {
		// do some stuff here it need be!
		initialize();
	}

	public static Angler getInstance() {

		if (instance == null) {
			instance = new Angler();
		}
		return instance;
	}

	public void initialize() {

	}
	
	public void up() { 
		talon.set(1);
	}

	public void down() { 
		talon.set(-1);
	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}
	public void stop(){
		talon.stopMotor();
	}
}

