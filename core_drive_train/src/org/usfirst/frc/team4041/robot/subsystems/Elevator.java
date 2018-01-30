package org.usfirst.frc.team4041.robot.subsystems;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

	static final Spark elevatorSpark = new Spark(RobotMap.elevatorSparkPWM);
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

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	private void initialize() {
		elevatorSpark.setSafetyEnabled(false);
	}

	public void up() {
		elevatorSpark.set(0.3);
	}

	public void down() {
		elevatorSpark.set(-0.3);
	}

	public void stop() {
		elevatorSpark.stopMotor();
	}
}
