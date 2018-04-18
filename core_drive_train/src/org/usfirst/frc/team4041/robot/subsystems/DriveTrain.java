package org.usfirst.frc.team4041.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;

import org.usfirst.frc.team4041.robot.RobotMap;
import org.usfirst.frc.team4041.robot.commands.teleop.ArcadeDrive;
import org.usfirst.frc.team4041.robot.subsystems.PID.ElevatorPID;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

 
public class DriveTrain extends Subsystem {
	
	private static final WPI_TalonSRX leftFront = new WPI_TalonSRX(RobotMap.leftFrontDriveTalonSRX);
	private static final WPI_TalonSRX leftRear = new WPI_TalonSRX(RobotMap.leftRearDriveTalonSRX);
	private static final WPI_TalonSRX rightFront = new WPI_TalonSRX(RobotMap.rightFrontDriveTalonSRX);
	private static final WPI_TalonSRX rightRear = new WPI_TalonSRX(RobotMap.rightRearDriveTalonSRX);

	private static final SpeedControllerGroup drivetrainLeft = new SpeedControllerGroup(leftFront, leftRear);
	private static final SpeedControllerGroup drivetrainRight = new SpeedControllerGroup(rightFront, rightRear);
	private static final DifferentialDrive robotDrive = new DifferentialDrive(drivetrainLeft,drivetrainRight);
	
	//ToDo: put encoder locations in RobotMap
	private static final Encoder leftEncoder = new Encoder(RobotMap.leftEncoderPt1, RobotMap.leftEncoderPt2, true, Encoder.EncodingType.k4X);
	private static final Encoder rightEncoder = new Encoder(RobotMap.rightEncoderPt1, RobotMap.rightEncoderPt2, false, Encoder.EncodingType.k4X);
	private static final ADXRS450_Gyro spiGyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
	
	private final double turningScaleLowElevator = 0.7;
	private final double powerScaleLowElevator = 0.8;
	
	private final double turningScaleHighElevator = 0.7;
	private final double powerScaleHighElevator = 0.5;
	
    private static DriveTrain instance;
    private static ElevatorPID elevator;
    
        private DriveTrain() {
    	
    	initialize();
    }
    
    public static DriveTrain getInstance() {
        if (instance == null) {
            instance = new DriveTrain();
        }
        return instance;
    }

    public void initialize() {
		robotDrive.setExpiration(1);
		double circumference = 18.8496;
		double pulsesPerRevolution = 360;
		double distancePerPulse = circumference/pulsesPerRevolution;
		
		leftEncoder.setDistancePerPulse(distancePerPulse);
		rightEncoder.setDistancePerPulse(distancePerPulse);
		
		elevator = ElevatorPID.getInstance();
		
		leftEncoder.reset();
		rightEncoder.reset();
		
		//ToDo: what the hell is set safety enabled?
		robotDrive.setSafetyEnabled(false);
		
		try {
			spiGyro.reset();
			spiGyro.calibrate();
		} catch (NullPointerException npe) {
			//eat this error
		}
		addInfoToDashBoard();
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new ArcadeDrive());
    }
    
    public void autoDrive(double speed, double rotation) {
    	addInfoToDashBoard();
    	robotDrive.curvatureDrive(speed, rotation, false);
    }
    
    public void tankDrive(double leftSpeed, double rightSpeed) {
    	addInfoToDashBoard();
    	robotDrive.tankDrive(leftSpeed, rightSpeed);
    }
    
	public void arcadeDrive(Joystick driverController, int moveAxis, int turnAxis) {
		
    	addInfoToDashBoard();
    	
    	double turningScalingFactor = turningScaleLowElevator;
    	double powerScalingFactor = powerScaleLowElevator;
    	
    	if(elevator.getCurrentElevatorHeight() > elevator.highTreshold) {
    		turningScalingFactor = turningScaleHighElevator;
        	powerScalingFactor = powerScaleHighElevator;
    	}
    	
    	double move = -driverController.getRawAxis(moveAxis) * powerScalingFactor;
    	double turn = driverController.getRawAxis(turnAxis) * turningScalingFactor;
    	
    	robotDrive.arcadeDrive(move,turn,true);
    }
    
    private void addInfoToDashBoard(){

    	SmartDashboard.putData("leftEncoder", leftEncoder);
    	SmartDashboard.putData("rightEncoder", rightEncoder);
    }
    
    public void stop(){
    	robotDrive.stopMotor();
    }
    
    public double getLeftEncoderDistance() 
    {
    	return leftEncoder.getDistance();
    }
    	
    public double getRightEncoderDistance() 
    {
    	return rightEncoder.getDistance();
    }    
    public void resetRightEncoder() 
    {
    	rightEncoder.reset();
    }
    
    public void resetLeftEncoder() 
    {
    	leftEncoder.reset();
    }
    public double getGyroAngle() 
    {
    	return spiGyro.getAngle();
    }
    
    public void resetGyro()
    {
    	spiGyro.reset();
    }
}

