package org.usfirst.frc.team4041.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Talon;
//import edu.wpi.first.wpilibj.Ultrasonic;

import org.usfirst.frc.team4041.robot.RobotMap;
import org.usfirst.frc.team4041.robot.commands.DriveWithController;

public class DriveTrain extends Subsystem {
	
	static final Talon leftTalon = new Talon(RobotMap.leftDriveTalon);
	static final Talon rightTalon = new Talon(RobotMap.rightDriveTalon);
	static final ADXRS450_Gyro spiGyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
	
	static final RobotDrive robotDrive = new RobotDrive(leftTalon, rightTalon);
	static final Encoder leftEncoder = new Encoder(RobotMap.leftEncooderDIO1, RobotMap.leftEncooderDIO2, false, Encoder.EncodingType.k4X);//e4t
	static final Encoder rightEncoder = new Encoder(RobotMap.rightEncooderDIO1, RobotMap.rightEncooderDIO12, true, Encoder.EncodingType.k4X);//e4p

//	static final Ultrasonic ultrasonic = new Ultrasonic(RobotMap.ultrasonicPing, RobotMap.ultrasonicEcho, Ultrasonic.Unit.kInches);
    
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
		robotDrive.setExpiration(0.1);
		leftEncoder.reset();
		rightEncoder.reset();
		leftEncoder.setDistancePerPulse(0.0027777778);
		rightEncoder.setDistancePerPulse(0.0027777778);
		rightEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
	
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
        setDefaultCommand(new DriveWithController());
    }

    public void tankDrive(double right, double left) { 
    	robotDrive.tankDrive(right, left, true);
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

    public RobotDrive getTrain() {
        return robotDrive;
    }
    
    public Encoder getRightEncoder() {
        return rightEncoder;
    }
    
    public Encoder getLeftEncoder() {
        return leftEncoder;
    }
    
    public void resetLeftEncoder(){
    	leftEncoder.reset();
    }
    
    public double getLeftDistance(){
    	return leftEncoder.getDistance();
    }
    
    public double getRightDistance(){
    	return rightEncoder.getDistance();
    }
    
    public boolean driveCurveLeft(double distance){
    	
    	boolean finished = true;
    	double currentDistance = Math.abs(leftEncoder.getDistance());
    	if(distance >  currentDistance){
    		finished = false;
    		if((currentDistance/distance) > .60 &&  (currentDistance/distance) < .80){
    			robotDrive.tankDrive(0.25, 0.65, true);
    		}
    		else{
    			robotDrive.tankDrive(0.45,0.45, true);
    		}
    		 
    	}
    	SmartDashboard.putData("Gyro", spiGyro);
    	SmartDashboard.putData("leftEncoder", leftEncoder);
    	SmartDashboard.putData("rightEncoder", rightEncoder);
    	return finished;
    }
    
    public boolean driveCurveRight(double distance){
    	
    	boolean finished = true;
    	double currentDistance = Math.abs(leftEncoder.getDistance());
    	if(distance >  currentDistance){
    		finished = false;
    		if((currentDistance/distance) > .65 && (currentDistance/distance) < .80){
    			robotDrive.tankDrive(0.75, 0.25, true);
    		}
    		else{
    			robotDrive.tankDrive(0.45, 0.45, true);
    		}
    	}
    	SmartDashboard.putData("Gyro", spiGyro);
    	SmartDashboard.putData("leftEncoder", leftEncoder);
    	SmartDashboard.putData("rightEncoder", rightEncoder);
    	return finished;
    }
    
    public boolean driveStraight(double speed, double distance, double tolerance){
    	
    	//System.out.println("in drive straight function");
    	SmartDashboard.putData("Gyro", spiGyro);
    	SmartDashboard.putData("leftEncoder", leftEncoder);
    	SmartDashboard.putData("rightEncoder", rightEncoder);
    	
    	boolean finished = true;
    	//double adjustment = 0.75;
    	
    	if(distance >  Math.abs(leftEncoder.getDistance()) && distance > Math.abs(rightEncoder.getDistance())){
    		finished = false;
    		//System.out.println("carry on my wayward son!");
    		
    		//the distances traveled by each side of the drive train
    		// are within tolerance, so just go forward
    		//System.out.println("straight and narrow");
    		robotDrive.tankDrive(speed,speed, true);        
        	
//        	if(tolerance - Math.abs(Math.abs(leftEncoder.getDistance()) - Math.abs(rightEncoder.getDistance()))  < 0){
//        		
//        		double difference = Math.abs(leftEncoder.getDistance()) - Math.abs(rightEncoder.getDistance());
//        		SmartDashboard.putNumber("difference",difference);
//        		//System.out.println("we have achieved wonkiness");
//        		//we're out odistanceDifferencef tolerance
//        		//lets figure out which side has travel further
//        		//adjustedSpeed = (speed + (Math.abs(leftEncoder.getDistance()) - Math.abs(rightEncoder.getDistance()))/10);
//        		if(Math.abs(leftEncoder.getDistance()) - Math.abs(rightEncoder.getDistance()) > 0){
//        			//the left side has traveled further than the right side
//        			robotDrive.tankDrive((speed + (adjustment)), 0.1, true);
//        			//System.out.println("increase right");
//        		}else{
//        			//the right side has traveled further than the left side
//        			robotDrive.tankDrive(0.1, (speed + adjustment), true);
//        			//System.out.println("increase left");
//        		}
//        	}
    	}
    	SmartDashboard.putData("Gyro", spiGyro);
    	SmartDashboard.putData("leftEncoder", leftEncoder);
    	SmartDashboard.putData("rightEncoder", rightEncoder);
    	return finished;
    }
 
//  private double ds_e = 0;
//  private double ds_kp = 0.03;
//  private final double Kp = 0.03;
//  
//  public void driveStraighter(double speed){
//  	ds_kp = 0.03;//SmartDashboard.getNumber("straight kp",0.03);
//  	double angle = spiGyro.getAngle();
//  	double output = -angle*Kp;
//  	
//  	if(Math.abs(output) < 0.4 && output != 0){
//  		output = (output/Math.abs(output)) * 0.4;
//  	}
//  	HDrive(speed, output);
//  }
//  
//  public void HDrive(double moveValue, double turnValue){
//  	robotDrive.arcadeDrive(-moveValue,turnValue);
//  } 
}

