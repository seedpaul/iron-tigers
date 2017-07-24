package org.usfirst.frc.team4041.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team4041.robot.RobotMap;
import org.usfirst.frc.team4041.robot.commands.TankDriveWithController;

public class DriveTrain extends Subsystem {
	
	static final Talon leftTalon = new Talon(RobotMap.leftDriveTalon);
	static final Talon rightTalon = new Talon(RobotMap.rightDriveTalon);
	static final ADXRS450_Gyro spiGyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
	
	static final RobotDrive robotDrive = new RobotDrive(leftTalon, rightTalon);
	static final Encoder leftEncoder = new Encoder(RobotMap.leftEncooderDIO1, RobotMap.leftEncooderDIO2, false, Encoder.EncodingType.k4X);//e4t
	static final Encoder rightEncoder = new Encoder(RobotMap.rightEncooderDIO1, RobotMap.rightEncooderDIO12, true, Encoder.EncodingType.k4X);//e4p

	private static double Kp = 0.03;
    
    private static DriveTrain instance;
    
    private DriveTrain() {
    	// do some stuff here it need be!
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

		leftEncoder.setDistancePerPulse(0.0027777778);
		rightEncoder.setDistancePerPulse(0.0027777778);
		
		leftEncoder.reset();
		rightEncoder.reset();
	
		try {
			spiGyro.reset();
			spiGyro.calibrate();
		} catch (NullPointerException npe) {
			//eat this error
		}

		robotDrive.setSafetyEnabled(false);
    	SmartDashboard.putData("Gyro", spiGyro);
    	SmartDashboard.putData("leftEncoder", leftEncoder);
    	SmartDashboard.putData("rightEncoder", rightEncoder);
    }
    
    public void stop(){
    	robotDrive.tankDrive(RobotMap.STOP, RobotMap.STOP);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new TankDriveWithController());
    }

    public void tankDrive(double right, double left) { 
    	robotDrive.tankDrive(right, left, true);
    	SmartDashboard.putData("Gyro", spiGyro);
    	SmartDashboard.putData("leftEncoder", leftEncoder);
    	SmartDashboard.putData("rightEncoder", rightEncoder);
    }
    
    public void arcadeDrive(Joystick driverController, int moveAxis, int turnAxis) { 

    	robotDrive.arcadeDrive(driverController.getRawAxis(1), (-1*driverController.getRawAxis(4)),true);
    	SmartDashboard.putData("Gyro", spiGyro);
    	SmartDashboard.putData("leftEncoder", leftEncoder);
    	SmartDashboard.putData("rightEncoder", rightEncoder);
    }
    
    public void resetGyro() {
    	spiGyro.reset();
    }

    public Gyro getGyro() {
        return spiGyro;
    }
    
    public double getAngle(){
    	return spiGyro.getAngle();
    }
    
    public Encoder getRightEncoder() {
        return rightEncoder;
    }
    
    public Encoder getLeftEncoder() {
        return leftEncoder;
    }
    
    public boolean turnLeft(double in_angle){
    	
    	SmartDashboard.putData("Gyro", spiGyro);
    	double angle = spiGyro.getAngle(); 
    	boolean retVal = true;
    	
    	if(angle >= in_angle){
    		robotDrive.tankDrive(0,0.5);
    		retVal = false;
    		
    	}
    	return retVal;
    }
    public boolean turnRight(double in_angle){
    	
    	SmartDashboard.putData("Gyro", spiGyro);
    	double angle = spiGyro.getAngle(); 
    	boolean retVal = true;
    	
    	if(angle <= in_angle){
    		robotDrive.tankDrive(0.5,0);
    		retVal = false;
    		
    	}
    	return retVal;
    }
    
    public boolean driveStraight(double speed, double distance){
    	
    	SmartDashboard.putData("Gyro", spiGyro);
    	SmartDashboard.putData("leftEncoder", leftEncoder);
    	SmartDashboard.putData("rightEncoder", rightEncoder);
    	
    	boolean finished = true;
    	double leftDistance = Math.abs(leftEncoder.getDistance());
    	double rightDistance = Math.abs(rightEncoder.getDistance());
    	
    	if(distance >= leftDistance && distance >= rightDistance){
    		
    		finished = false;
    		double angle = spiGyro.getAngle(); // get current headinge
    		robotDrive.drive(speed, -angle*Kp); // drive towards heading 0
    		Timer.delay(0.004);     
    	}
    	else{
    		System.out.println("!!!!!!we gone far enough!!!!!");
    		System.out.println("leftDistance: " + leftDistance);
    		System.out.println("rightDistance: " + rightDistance);
    	}
    	SmartDashboard.putData("Gyro", spiGyro);
    	SmartDashboard.putData("leftEncoder", leftEncoder);
    	
    	SmartDashboard.putData("rightEncoder", rightEncoder);
    	return finished;
    }
}

