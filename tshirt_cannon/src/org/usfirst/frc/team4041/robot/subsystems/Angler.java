package org.usfirst.frc.team4041.robot.subsystems;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Angler extends Subsystem {
	
	private static Angler instance;

	static final Jaguar jaguar = new Jaguar(RobotMap.cannonJaquar);

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
		jaguar.set(-1);
	}

	public void down() { 
		jaguar.set(1);
	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}
	public void stop(){
		jaguar.stopMotor();
	}
}

