package org.usfirst.frc.team4041.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;

import org.usfirst.frc.team4041.robot.RobotMap;
import org.usfirst.frc.team4041.robot.commands.ArcadeDriveWithController;

public class DriveTrain extends Subsystem {
	
	static final Talon leftTalon = new Talon(RobotMap.leftDriveTalon);
	static final Talon rightTalon = new Talon(RobotMap.rightDriveTalon);
	
	static final DifferentialDrive robotDrive = new DifferentialDrive(leftTalon, rightTalon);
	
	//ToDo: put encoder locations in RobotMap
	static final Encoder leftEncoder = new Encoder(0,1,true,Encoder.EncodingType.k4X);
	static final Encoder rightEncoder = new Encoder(2,3,false,Encoder.EncodingType.k4X);
	
	static final ADXRS450_Gyro spiGyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

    
    private static DriveTrain instance;
    
    private DriveTrain() {
    	// do some stuff here if need be!
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
    	addInfoToDashBoard();SmartDashboard.putData("Gyro", spiGyro);
    	SmartDashboard.putData("leftEncoder", leftEncoder);
    	SmartDashboard.putData("rightEncoder", rightEncoder);
    	robotDrive.tankDrive(leftSpeed, rightSpeed);
    }
    
    public void arcadeDrive(Joystick driverController, int moveAxis, int turnAxis) {
    	addInfoToDashBoard();
    	double left = driverController.getRawAxis(1);
    	double right = -1*driverController.getRawAxis(4);
    	robotDrive.arcadeDrive(left,right,true);
    }
    
    private void addInfoToDashBoard(){
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
    
    public boolean getRightEncoderDirection()
    {
    	return rightEncoder.getDirection();
    }
    
    public boolean getLeftEncoderDirection() 
    {
    	return leftEncoder.getDirection();
    }
    
    public double getLeftEncoderRate() 
    {
    	return leftEncoder.getRate();
    }
    
    public double getRightEncoderRate()
    {
    	return rightEncoder.getRate();
    }
    
    public boolean getLeftEncoderGetStopped() 
    {
    	return leftEncoder.getStopped();
    }
    
    public boolean getRightEncoderGetStopped() 
    {
    	return rightEncoder.getStopped();
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

