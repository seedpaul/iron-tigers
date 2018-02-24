package org.usfirst.frc.team4041.robot.subsystems.PID;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorPID extends PIDSubsystem {

	private static final Talon elevatorTalon = new Talon(RobotMap.elevatorTalonPWM);
	private static final Encoder elevatorEncoder = new Encoder(RobotMap.elevatorEncoderPt1, RobotMap.elevatorEncoderPt2, false, Encoder.EncodingType.k4X);
	
	private static double minInput = 0.0;
	private static double maxInput = 3850.0;
	
	private static double minOutput = -0.8;
	private static double maxOutput = 0.4;
	private static double absoluteTolerance = 4.0;
	
	private static double startingHeight = 0.0;
	private static double transportHeight = 300.0;
	private static double switchHeight = 1200.0;
	private static double scaleHeight = 3850.0;
	public double highTreshold = 2500.0;
	
	private static double[] setPoints = {startingHeight,transportHeight,switchHeight,scaleHeight};
	private static int currentSetPointIndex = 0; 
	
	private static double p = -0.008;
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
		elevatorEncoder.setDistancePerPulse(1);
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
	}

    protected double returnPIDInput() {
    	addInfoToDashBoard();
    	return elevatorEncoder.getDistance(); // returns the sensor value that is providing the feedback for the system
    }

    protected void usePIDOutput(double output) {
    	elevatorTalon.pidWrite(output); // this is where the computed output value from the PIDController is applied to the motor
    	addInfoToDashBoard();
    }
    
    public void upToSwitchHeight() {
    	this.setSetpoint(switchHeight);
    	addInfoToDashBoard();
    }
    
    public void upToScaleHeight() {
    	this.setSetpoint(scaleHeight);
    	addInfoToDashBoard();
    }
    
    public void upToTransportHeight() {
    	this.setSetpoint(transportHeight);
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
	
	public boolean isComplete() {
		return this.onTarget();
	}
	
	public void teleopUp() {
		this.disablePID();
		elevatorTalon.set(-0.8);
		addInfoToDashBoard();
	}
	
	public void teleopDown() {
		this.disablePID();
		elevatorTalon.set(0.8);
		addInfoToDashBoard();
	}
	
	private void disablePID() {
		if(this.getPIDController().isEnabled()) {
			this.disable();
		}
	}
	
	public double getCurrentElevatorHeight() {
		return elevatorEncoder.getDistance(); 
	}
	
 
	public void stop() {
		elevatorTalon.stopMotor();
		addInfoToDashBoard();
	}
	
    private void addInfoToDashBoard(){
    	SmartDashboard.putData("elevatorEncoder", elevatorEncoder);
    }
}
