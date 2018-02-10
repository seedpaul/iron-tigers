package org.usfirst.frc.team4041.robot.subsystems;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {

	private static final VictorSP elevatorVictor = new VictorSP(RobotMap.elevatorVictorSPPWM);
	private static final Encoder elevatorEncoder = new Encoder(RobotMap.elevatorEncoderPt1, RobotMap.elevatorEncoderPt2, false, Encoder.EncodingType.k4X);
	
	//values used to calculate the distance per pulse
	private static double circumference = 5.75;
	private static double gearToShaftRatio = 1.2;
	private static double pulsesPerRevolution = 12;
//	
//	private static double minInput = 0.0;
//	private static double maxInput = 500.0;
//	
//	private static double minOutput = -1.0;
//	private static double maxOutput = 1.0;
//	private static double absoluteTolerance = 1.0;
//	
//	private static double startingHeight = 80.0;
//	private static double transportHeight = 150.0;
//	private static double switchHeight = 200.0;
//	private static double scaleHeight = 300.0;
//	
//	private static double[] setPoints = {startingHeight,transportHeight,switchHeight,scaleHeight};
//	private static int currentSetPointIndex = 0; 
	
	private static double p = -0.02;
	private static double i = 0.0;
	private static double d = 0.0;
	private static double f = 0.0;
	
	private static Elevator instance;

	public Elevator() {
		//super("Elevator",p,i,d,f);
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

//    protected double returnPIDInput() {
//    	return elevatorEncoder.getDistance(); // returns the sensor value that is providing the feedback for the system
//    }
//
//    protected void usePIDOutput(double output) {
//    	elevatorSpark.pidWrite(output); // this is where the computed output value fromthe PIDController is applied to the motor
//    	addInfoToDashBoard();
//    }
	
	private void initialize() {
		
		elevatorVictor.setSafetyEnabled(false);
		elevatorEncoder.setDistancePerPulse((circumference*gearToShaftRatio)/pulsesPerRevolution);
		elevatorEncoder.reset();
		
//		this.setAbsoluteTolerance(absoluteTolerance);
//		this.getPIDController().setContinuous(false);
//		this.setSetpoint(setPoints[0]);
//		this.setInputRange(minInput, maxInput);
//		this.setOutputRange(minOutput, maxOutput);
		//this.enable();
		
		addInfoToDashBoard();
	}

//	public void up() {
//		currentSetPointIndex = (currentSetPointIndex < 3)? currentSetPointIndex + 1: 3;
//		this.setSetpoint(setPoints[currentSetPointIndex]);
//		addInfoToDashBoard();
//	}
//
//	public void down() {
//		currentSetPointIndex = (currentSetPointIndex > 0)? currentSetPointIndex - 1: 0;
//		this.setSetpoint(setPoints[currentSetPointIndex]);
//		addInfoToDashBoard();
//	}
	
	public void upSimple() {
		//this.disable();
		elevatorVictor.set(-1);
		addInfoToDashBoard();
	}

	public void downSimple() {
		//this.disable();
		elevatorVictor.set(1);
		addInfoToDashBoard();
	}

	public void stop() {
		elevatorVictor.stopMotor();
		addInfoToDashBoard();
	}
	
    public void disablePID() {
    	//this.disable();
    }
	
    private void addInfoToDashBoard(){
    	SmartDashboard.putData("elevatorEncoder", elevatorEncoder);
    	SmartDashboard.putData("elevatorSpark", elevatorVictor);
    }
}
