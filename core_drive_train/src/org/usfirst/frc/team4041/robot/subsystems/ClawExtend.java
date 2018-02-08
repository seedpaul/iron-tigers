package org.usfirst.frc.team4041.robot.subsystems;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class ClawExtend extends PIDSubsystem {
	
	//range of motion is about 75 degrees
	private static double minInput = 0.0;
	private static double maxInput = 300.0;
	private static double minOutput = -1.0;
	private static double maxOutput = 1.0;
	private static double absoluteTolerance = 0.10;
	private static double setPoint = 115.0;
	
	private static double p = 0.04;
	private static double i = 0.00001;
	private static double d = 0.0;
	private static double f = 0.7;
	
	private static double fullRange = 300.0;
	private static double offset = 0.0;

	// for each botton click move arm by 5 degrees
	int increment = 5;
	
	static final Spark clawExtendSpark = new Spark(RobotMap.clawExtendSparkPWM);
	private static AnalogPotentiometer angle = new AnalogPotentiometer(new AnalogInput(RobotMap.potDIO), fullRange, offset);
	private static ClawExtend instance;
	
	public ClawExtend() {
		super("ClawExtend", p, i, d, f);
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

    protected double returnPIDInput() {
    	return angle.get(); // returns the sensor value that is providing the feedback for the system
    }

    protected void usePIDOutput(double output) {
    	clawExtendSpark.pidWrite(output); // this is where the computed output value fromthe PIDController is applied to the motor
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
		System.out.println(this.getSetpoint());
	}

	public void down() {
		setPoint -= increment;
		this.setSetpoint(setPoint);
		System.out.println(this.getSetpoint());
	}

	public void stop() {
		clawExtendSpark.stopMotor();
	}
	
    public double getAngle() {
    	return angle.get();
    }
}
