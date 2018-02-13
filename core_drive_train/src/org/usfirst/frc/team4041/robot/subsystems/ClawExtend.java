package org.usfirst.frc.team4041.robot.subsystems;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ClawExtend extends Subsystem {
	
	
	static final Spark clawExtendSpark = new Spark(RobotMap.clawExtendSparkPWM);
	private static double fullRange = 300.0;
	private static double offset = 0.0;

	// for each botton click move arm by 5 degrees
	int increment = 5;
	
	//static final Spark clawExtendSpark = new Spark(RobotMap.clawExtendSparkPWM);
	public static AnalogPotentiometer angle = new AnalogPotentiometer(new AnalogInput(RobotMap.potDIO), fullRange, offset);
	private static ClawExtend instance;
	
	public ClawExtend() {
		this.initialize();
	}
	
	public static ClawExtend getInstance() {
		if (instance == null) {
			instance = new ClawExtend();
		}
		return instance;
	}

    public void initDefaultCommand() {
    }

	private void initialize() {
		
		System.out.println("initialize ClawExtend PIDSubsystem");
		clawExtendSpark.setSafetyEnabled(false);
		SmartDashboard.putData("pot",angle);
	}
    
	public void up() {
		clawExtendSpark.set(-0.5);
		SmartDashboard.putData("pot",angle);
	}

	public void down() {
		clawExtendSpark.set(0.3);
		SmartDashboard.putData("pot",angle);
	}

	public void stop() {
		clawExtendSpark.stopMotor();
	}
}
