package org.usfirst.frc.team4041.robot.subsystems.PID;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ClawExtendPID extends PIDSubsystem {
	
	//range of motion is about 75 degrees
	private static double minInput = 50.0;
	private static double maxInput = 125.0;
	private static double minOutput = -0.85;
	private static double maxOutput = 0.3;
	private static double auto_maxOutput = 0.3;
	private static double absoluteTolerance = 2.0;
	private static double setPoint = 50.0;
	
	private static double p = -0.017;
	private static double i = 0.0;
	private static double d = 0.0;
	private static double f = 0.0;
	
	private static boolean allowChange = true;
	
	private static double fullRange = 200.0;
	private static double offset = 0.0;

	// for each button click move arm by 5 degrees
	int increment = 10;
	private static final Spark clawExtendSpark = new Spark(RobotMap.clawExtendSparkPWM);
	private static AnalogInput potInput = new AnalogInput(RobotMap.potAIO);
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
    	return angle.get();// returns the sensor value that is providing the feedback for the system
    }

    protected void usePIDOutput(double output) {
    	clawExtendSpark.pidWrite(output); 
    }

	private void initialize() {
		
		clawExtendSpark.setSafetyEnabled(false);
		
		this.setAbsoluteTolerance(absoluteTolerance);
		this.getPIDController().setContinuous(false);
		this.setSetpoint(setPoint);
		this.setInputRange(minInput, maxInput);
		this.setOutputRange(minOutput, auto_maxOutput);
		this.enable();
		addInfoToDashBoard();
	}
	
	private void changeMaxOuptut() {
		if(allowChange){
			this.setOutputRange(minOutput, maxOutput);
			allowChange = false;
		}
	}
    
	public void up() {
		this.changeMaxOuptut();
		if((setPoint + increment) < maxInput){
			setPoint += increment;
		}
		else {
			setPoint = maxInput; 
		}
		System.out.println(setPoint);
		this.setSetpoint(setPoint);
		addInfoToDashBoard();
	}

	public void down() {
		this.changeMaxOuptut();
		if((setPoint - increment) > minInput){
			setPoint -= increment;
		}
		else {
			setPoint = minInput; 
		}
		System.out.println(setPoint);
		this.setSetpoint(setPoint);
		addInfoToDashBoard();
	}

	public void stop() {
		clawExtendSpark.stopMotor();
		addInfoToDashBoard();
	}
	
	public void moveToHorizontal() {
		this.setOutputRange(minOutput, auto_maxOutput);
		this.setSetpoint(50.0);
		addInfoToDashBoard();
	}
	
	public void moveToVertical() {
		this.setOutputRange(minOutput, auto_maxOutput);
		this.setSetpoint(120.0);
		addInfoToDashBoard();
	}
	
	public void moveToScaleShoot() {
		this.setOutputRange(minOutput, auto_maxOutput);
		this.setSetpoint(85.0);
		addInfoToDashBoard();
	}
	
    public double getAngle() {
    	return Math.round(angle.get());
    }
    
	public boolean isComplete() {
		return this.onTarget();
	}
	
    private void addInfoToDashBoard(){

    	SmartDashboard.putNumber("pot", getAngle());
    	SmartDashboard.putData("angle", angle);
    }
}
