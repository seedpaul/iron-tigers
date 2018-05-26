package org.usfirst.frc.team4041.robot.subsystems;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

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

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    private void initialize() {
		clawIntakeLeftSpark.setSafetyEnabled(false);
		clawIntakeRightSpark.setSafetyEnabled(false);
	}

	public void in() {
		clawIntakeLeftSpark.set(-0.75);//clockwise
		clawIntakeRightSpark.set(0.75);//counter-clockwise
	}

	public void out() {
		clawIntakeLeftSpark.set(0.75);//counter-clockwise
		clawIntakeRightSpark.set(-0.75);//clockwise
	}
	public void shoot() {
		clawIntakeLeftSpark.set(1);//counter-clockwise
		clawIntakeRightSpark.set(-1);//clockwise
	}
	public void slow() {
		clawIntakeLeftSpark.set(-0.20);//clockwise
		clawIntakeRightSpark.set(0.20);//counter-clockwise
	}

	public void stop() {
		System.out.println("stop");
		clawIntakeLeftSpark.stopMotor();
		clawIntakeRightSpark.stopMotor();
	}
	
	public void adjustable(double OperatorSpeed) {
		if(OperatorSpeed > 0) {
			clawIntakeLeftSpark.set(OperatorSpeed);//counter-clockwise
			clawIntakeRightSpark.set(-OperatorSpeed);//clockwise
		}
	}
}
