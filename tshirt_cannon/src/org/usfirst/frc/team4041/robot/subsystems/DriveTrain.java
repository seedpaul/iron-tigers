package org.usfirst.frc.team4041.robot.subsystems;

import org.usfirst.frc.team4041.robot.RobotMap;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	private static DriveTrain instance;

	static final Jaguar leftJaguar = new Jaguar(RobotMap.leftDriveJaguar);
	static final Jaguar rightJaguar = new Jaguar(RobotMap.rightDriveJaguar);
	static final RobotDrive robotDrive = new RobotDrive(leftJaguar, rightJaguar);


	// Put methods for controlling this subsystem
	// here. Call these from Commands.
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
		robotDrive.setSafetyEnabled(false);
	}

	public void tankDrive(double right, double left) { 
		robotDrive.tankDrive(right, left, true);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}

	public void stop(){
		robotDrive.tankDrive(RobotMap.Stop, RobotMap.Stop);
	}


}

