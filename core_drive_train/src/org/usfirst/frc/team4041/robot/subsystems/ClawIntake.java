package org.usfirst.frc.team4041.robot.subsystems;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClawIntake extends Subsystem {
	
	static final Spark clawIntakeLeftSpark = new Spark(RobotMap.clawIntakeRightSparkPWM);
	static final Spark clawIntakeRightSpark = new Spark(RobotMap.clawIntakeLeftSparkPWM);
	
	private static ClawIntake instance;

	public ClawIntake() {
		initialize();
	}

	public static ClawIntake getInstance() {
		if (instance == null) {
			instance = new ClawIntake();
		}
		return instance;
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    private void initialize() {
		clawIntakeLeftSpark.setSafetyEnabled(false);
		clawIntakeRightSpark.setSafetyEnabled(false);
	}

	public void in() {
		clawIntakeLeftSpark.set(0.3);//clockwise
		clawIntakeRightSpark.set(-0.3);//counter-clockwise
	}

	public void out() {
		clawIntakeLeftSpark.set(-0.3);//counter-clockwise
		clawIntakeRightSpark.set(0.3);//clockwise
	}

	public void stop() {
		clawIntakeLeftSpark.stopMotor();
		clawIntakeRightSpark.stopMotor();
	}
}

