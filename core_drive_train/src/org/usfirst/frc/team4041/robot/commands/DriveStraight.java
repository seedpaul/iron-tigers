package org.usfirst.frc.team4041.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveStraight extends CommandBase {
	private double myDistance;
	private double mySpeed;
	private double leftEncoderDistance;
	private double rightEncoderDistance;
	private boolean complete;
	private static double Kp = 0.027777777777778;

    private DriveStraight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires((Subsystem) driveTrain);
    }
    
    public DriveStraight(double distance, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this();
    	myDistance = distance;
    	mySpeed = speed;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	complete = false;
    	leftEncoderDistance = 0.0;
    	rightEncoderDistance = 0.0;
    	driveTrain.resetLeftEncoder();
    	driveTrain.resetRightEncoder();
    	driveTrain.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
 
    	leftEncoderDistance = driveTrain.getLeftEncoderDistance();
    	rightEncoderDistance = driveTrain.getRightEncoderDistance();
    	
    	if (leftEncoderDistance <= myDistance || rightEncoderDistance <= myDistance) {
    		double angle = driveTrain.getGyroAngle(); // get current heading
    		driveTrain.autoDrive(mySpeed, -(angle*Kp)); // drive towards heading 0
    		Timer.delay(0.004); 
    	} else {
    		complete = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return complete;
    }

    // Called once after isFinished returns true
    protected void end() {
    	leftEncoderDistance = 0.0;
    	rightEncoderDistance = 0.0;
    	driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {

    	leftEncoderDistance = 0.0;
    	rightEncoderDistance = 0.0;
    	driveTrain.stop();
    }
}
