package org.usfirst.frc.team4041.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.Encoder;
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
	static final Encoder leftEncoder = new Encoder(RobotMap.leftEncooderDIO1, RobotMap.leftEncooderDIO2, true, Encoder.EncodingType.k4X);
	static final Encoder rightEncoder = new Encoder(RobotMap.rightEncooderDIO1, RobotMap.rightEncooderDIO12, false, Encoder.EncodingType.k4X);

//	static final Ultrasonic ultrasonic = new Ultrasonic(RobotMap.ultrasonicPing, RobotMap.ultrasonicEcho, Ultrasonic.Unit.kInches);
    
    public static DriveTrain instance;
    
    private DriveTrain() {
    	// do some stuff here it need be!
    }
    
    public static DriveTrain getInstance() {
        if (instance == null) {
            instance = new DriveTrain();
        }
        return instance;
    }

    public void initialize() {
		robotDrive.setExpiration(0.1);
		
		leftEncoder.setDistancePerPulse(0.00277778);
		rightEncoder.setDistancePerPulse(0.00277778);

		try {
			spiGyro.reset();
		} catch (NullPointerException npe) {
			//eat this error
		}

		robotDrive.setSafetyEnabled(false);
    }

    protected void initDefaultCommand() {
        this.setDefaultCommand(new DriveWithController());
    }

    public void tankDrive(double right, double left) { 
    	robotDrive.tankDrive(right, left);
    }

    public Gyro getGyro() {
        return spiGyro;
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

//    public double getDistanceToWall() {
//        ultrasonic.ping();
//        try {
//            Thread.sleep(30);
//        } 
//        catch (Exception ex) {
//        	// eat the delicious exception
//        }
//        return ultrasonic.getRangeInches();
//    }

}

