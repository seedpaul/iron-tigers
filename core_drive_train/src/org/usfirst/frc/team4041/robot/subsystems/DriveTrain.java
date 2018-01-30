package org.usfirst.frc.team4041.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;

import org.usfirst.frc.team4041.robot.RobotMap;
import org.usfirst.frc.team4041.robot.commands.ArcadeDriveWithController;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DriveTrain extends Subsystem {
	
	static final WPI_TalonSRX leftFront = new WPI_TalonSRX(RobotMap.leftFrontDriveTalonSRX);
	static final WPI_TalonSRX leftRear = new WPI_TalonSRX(RobotMap.leftRearDriveTalonSRX);
	static final WPI_TalonSRX rightFront = new WPI_TalonSRX(RobotMap.rightFrontDriveTalonSRX);
	static final WPI_TalonSRX rightRear = new WPI_TalonSRX(RobotMap.rightRearDriveTalonSRX);

	static final SpeedControllerGroup drivetrainLeft = new SpeedControllerGroup(leftFront, leftRear);
	static final SpeedControllerGroup drivetrainRight = new SpeedControllerGroup(rightFront, rightRear);
	static final DifferentialDrive robotDrive = new DifferentialDrive(drivetrainLeft,drivetrainRight);
	
	//ToDo: put encoder locations in RobotMap
	static final Encoder leftEncoder = new Encoder(RobotMap.leftEncoderPt1, RobotMap.leftEncoderPt2, true, Encoder.EncodingType.k4X);
	static final Encoder rightEncoder = new Encoder(RobotMap.rightEncoderPt1, RobotMap.rightEncoderPt2, false, Encoder.EncodingType.k4X);
	static final ADXRS450_Gyro spiGyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
	
	static final RangeFinder rangeFinder = RangeFinder.getInstance();
	
    private static DriveTrain instance;
    
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
		
		leftEncoder.reset();
		rightEncoder.reset();
		
		//ToDo: what the hell is set saftey enabled?
		robotDrive.setSafetyEnabled(false);
		
		try {
			spiGyro.reset();
			spiGyro.calibrate();
		} catch (NullPointerException npe) {
			//eat this error
		}
		addInfoToDashBoard();
    }

    //Audrey was here, paul was here too!
    protected void initDefaultCommand() {
        setDefaultCommand(new ArcadeDriveWithController());
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
    	robotDrive.arcadeDrive(-driverController.getRawAxis(moveAxis), (driverController.getRawAxis(turnAxis)),true);
    }
    
    private void addInfoToDashBoard(){
    	
    	SmartDashboard.putNumber("Left Ultrasonic Distance", rangeFinder.getLeftSensorDistance());
    	SmartDashboard.putNumber("Right Ultrasonic Distance", rangeFinder.getRightSensorDistance());
    	
    	SmartDashboard.putData("Gyro", spiGyro);
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

