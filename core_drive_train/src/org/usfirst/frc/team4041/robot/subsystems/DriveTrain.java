package org.usfirst.frc.team4041.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

import org.usfirst.frc.team4041.robot.RobotMap;
import org.usfirst.frc.team4041.robot.commands.ArcadeDriveWithController;

public class DriveTrain extends Subsystem {
	
	static final Talon leftTalon = new Talon(RobotMap.leftDriveTalon);
	static final Talon rightTalon = new Talon(RobotMap.rightDriveTalon);
	
	static final DifferentialDrive robotDrive = new DifferentialDrive(leftTalon, rightTalon);

    
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
		robotDrive.setSafetyEnabled(false);
    }

    //Audrey was here, paul was here too!
    protected void initDefaultCommand() {
        setDefaultCommand(new ArcadeDriveWithController());
    }
    
    public void arcadeDrive(Joystick driverController, int moveAxis, int turnAxis) { 
    	double left = driverController.getRawAxis(1);
    	//left = left * 0.7;
    	double right = -1*driverController.getRawAxis(4);
    	//right = right * 0.5;
    	robotDrive.arcadeDrive(left,right,true);
    }
    public void stop(){
    	robotDrive.stopMotor();
    }
    
}

