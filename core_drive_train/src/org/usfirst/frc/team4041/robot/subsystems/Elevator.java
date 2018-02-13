package org.usfirst.frc.team4041.robot.subsystems;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {

	private static final Talon elevatorTalon = new Talon(RobotMap.elevatorTalonPWM);

	
	private static Elevator instance;

	public Elevator() {
		initialize();
	}

	public static Elevator getInstance() {
		if (instance == null) {
			instance = new Elevator();
		}
		return instance;
	}
	
	private void initialize() {
		
		elevatorTalon.setSafetyEnabled(false);
		addInfoToDashBoard();
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void up() {
		elevatorTalon.set(-1);
		addInfoToDashBoard();
	}

	public void down() {
		elevatorTalon.set(1);
		addInfoToDashBoard();
	}

	public void stop() {
		elevatorTalon.stopMotor();
		addInfoToDashBoard();
	}
	
    private void addInfoToDashBoard(){
    	SmartDashboard.putData("elevatorSpark", elevatorTalon);
    }
}
