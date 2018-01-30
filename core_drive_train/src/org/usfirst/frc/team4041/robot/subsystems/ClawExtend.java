package org.usfirst.frc.team4041.robot.subsystems;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClawExtend extends Subsystem {

	static final Spark clawExtendSpark = new Spark(RobotMap.clawExtendSparkPWM);
	private static ClawExtend instance;

	public ClawExtend() {
		initialize();
	}

	public static ClawExtend getInstance() {
		if (instance == null) {
			instance = new ClawExtend();
		}
		return instance;
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	private void initialize() {
		clawExtendSpark.setSafetyEnabled(false);
	}

	public void up() {
		clawExtendSpark.set(0.3);
	}

	public void down() {
		clawExtendSpark.set(-0.3);
	}

	public void stop() {
		clawExtendSpark.stopMotor();
	}
}
