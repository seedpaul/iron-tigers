package org.usfirst.frc.team4041.robot.subsystems;

import org.usfirst.frc.team4041.robot.RobotMap;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {

	private static DriveTrain instance;

	static final Jaguar leftJaguar = new Jaguar(RobotMap.leftDriveJaguar);
	static final Jaguar rightJaguar = new Jaguar(RobotMap.rightDriveJaguar);
	static final DifferentialDrive robotDrive = new DifferentialDrive(leftJaguar,rightJaguar);
	private final double turningScale = 0.8;
	private final double powerScale = 0.8;

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
	
	public void arcadeDrive(Joystick driverController, int moveAxis, int turnAxis) {
    	
    	double move = -driverController.getRawAxis(moveAxis) * this.powerScale;
    	double turn = driverController.getRawAxis(turnAxis) * this.turningScale;
    	
    	robotDrive.arcadeDrive(move,turn,true);
    }

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}

	public void stop(){
		robotDrive.tankDrive(RobotMap.Stop, RobotMap.Stop);
	}


}

