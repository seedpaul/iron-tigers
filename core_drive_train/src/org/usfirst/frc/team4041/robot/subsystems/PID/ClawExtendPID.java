package org.usfirst.frc.team4041.robot.subsystems.PID;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ClawExtendPID extends PIDSubsystem {
	
	//range of motion is about 75 degrees
	private static double minInput = 0.0;
	private static double maxInput = 60.0;
	private static double minOutput = -0.7;
	private static double maxOutput = 0.3;
	private static double absoluteTolerance = 1.0;
	private static double setPoint = 0.0;
	
	private static double p = -0.025;
	private static double i = 0.002;
	private static double d = 0.01;
	private static double f = 0.0;
	
	private static double fullRange = 10000.0;
	private static double offset = -515.0;

	// for each button click move arm by 5 degrees
	int increment = 10;
	private static final Spark clawExtendSpark = new Spark(RobotMap.clawExtendSparkPWM);
	private static AnalogInput potInput = new AnalogInput(RobotMap.potDIO);
	private static AnalogPotentiometer angle = new AnalogPotentiometer(potInput, fullRange, offset);
	private static ClawExtendPID instance;
	
	public ClawExtendPID() {
		super("ClawExtend", p, i, d, f);
		this.initialize();
	}
	
	public static ClawExtendPID getInstance() {
		if (instance == null) {
			instance = new ClawExtendPID();
		}
		return instance;
	}

    public void initDefaultCommand() {
    }

    protected double returnPIDInput() {
    	SmartDashboard.putNumber("claw angle:",this.getMutedAngle());
    	return Math.round(angle.get());// returns the sensor value that is providing the feedback for the system
    }

    protected void usePIDOutput(double output) {
    	clawExtendSpark.pidWrite(output); 
    }

	private void initialize() {
		
		System.out.println("initialize ClawExtend PIDSubsystem");
		
		clawExtendSpark.setSafetyEnabled(false);
		
		this.setAbsoluteTolerance(absoluteTolerance);
		this.getPIDController().setContinuous(false);
		this.setSetpoint(setPoint);
		this.setInputRange(minInput, maxInput);
		this.setOutputRange(minOutput, maxOutput);
		this.enable();
	}
    
	public void up() {
		setPoint += increment;
		this.setSetpoint(setPoint);
		//Timer.delay(1);
		System.out.println(this.getSetpoint());
		SmartDashboard.putNumber("claw angle:",this.getMutedAngle());
	}

	public void down() {
		setPoint -= increment;
		this.setSetpoint(setPoint);
		//Timer.delay(1);
		System.out.println(this.getSetpoint());
		SmartDashboard.putNumber("claw angle:",this.getMutedAngle());
	}

	public void stop() {
		clawExtendSpark.stopMotor();
		SmartDashboard.putNumber("claw angle:",this.getMutedAngle());
	}
	
    public double getAngle() {
    	SmartDashboard.putNumber("claw angle:",this.getMutedAngle());
    	return Math.round(angle.get());
    	
    }
    
	private double getMutedAngle() {
		return Math.round(angle.get());
	}
}
