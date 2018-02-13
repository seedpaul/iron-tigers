package org.usfirst.frc.team4041.robot.subsystems.PID;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorPID extends PIDSubsystem {

	private static final Talon elevatorTalon = new Talon(RobotMap.elevatorTalonPWM);
	private static final Encoder elevatorEncoder = new Encoder(RobotMap.elevatorEncoderPt1, RobotMap.elevatorEncoderPt2, false, Encoder.EncodingType.k4X);
	
	//values used to calculate the distance per pulse
	private static double circumference = 5.75;
	private static double gearToShaftRatio = 1.2;
	private static double pulsesPerRevolution = 12;
	
	private static double minInput = 0.0;
	private static double maxInput = 500.0;
	
	private static double minOutput = -1.0;
	private static double maxOutput = 1.0;
	private static double absoluteTolerance = 1.0;
	
	private static double startingHeight = 80.0;
	private static double transportHeight = 150.0;
	private static double switchHeight = 200.0;
	private static double scaleHeight = 300.0;
	
	private static double[] setPoints = {startingHeight,transportHeight,switchHeight,scaleHeight};
	private static int currentSetPointIndex = 0; 
	
	private static double p = -0.02;
	private static double i = 0.0;
	private static double d = 0.0;
	private static double f = 0.0;
	
	private static ElevatorPID instance;

	public ElevatorPID() {
		super("ElevatorPID",p,i,d,f);
		initialize();
	}

	public static ElevatorPID getInstance() {
		if (instance == null) {
			instance = new ElevatorPID();
		}
		return instance;
	}
	
	private void initialize() {
		
		elevatorTalon.setSafetyEnabled(false);
		elevatorEncoder.setDistancePerPulse((circumference*gearToShaftRatio)/pulsesPerRevolution);
		elevatorEncoder.reset();
		
		this.setAbsoluteTolerance(absoluteTolerance);
		this.getPIDController().setContinuous(false);
		this.setSetpoint(setPoints[0]);
		this.setInputRange(minInput, maxInput);
		this.setOutputRange(minOutput, maxOutput);
		
		this.enable();
		
		addInfoToDashBoard();
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

    protected double returnPIDInput() {
    	return elevatorEncoder.getDistance(); // returns the sensor value that is providing the feedback for the system
    }

    protected void usePIDOutput(double output) {
    	elevatorTalon.pidWrite(output); // this is where the computed output value fromthe PIDController is applied to the motor
    	addInfoToDashBoard();
    }
	
	public void up() {
		currentSetPointIndex = (currentSetPointIndex < 3)? currentSetPointIndex + 1: 3;
		this.setSetpoint(setPoints[currentSetPointIndex]);
		addInfoToDashBoard();
	}

	public void down() {
		currentSetPointIndex = (currentSetPointIndex > 0)? currentSetPointIndex - 1: 0;
		this.setSetpoint(setPoints[currentSetPointIndex]);
		addInfoToDashBoard();
	}

	public void stop() {
		elevatorTalon.stopMotor();
		addInfoToDashBoard();
	}
	
    private void addInfoToDashBoard(){
    	SmartDashboard.putData("elevatorEncoder", elevatorEncoder);
    	SmartDashboard.putData("elevatorSpark", elevatorTalon);
    }
}
