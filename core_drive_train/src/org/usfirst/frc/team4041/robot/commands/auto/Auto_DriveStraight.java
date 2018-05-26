package org.usfirst.frc.team4041.robot.commands.auto;

import org.usfirst.frc.team4041.robot.commands.teleop.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Auto_DriveStraight extends CommandBase {
	private double myDistance;
	private double targetSpeed;
	private double currentSpeed;
	private double leftEncoderDistance;
	private double rightEncoderDistance;
	private boolean complete;
	private static double Kp = 0.095;

    private Auto_DriveStraight() {;
    	requires((Subsystem) driveTrain);
    }
    
    public Auto_DriveStraight(double distance, double speed) {
    	this();
    	myDistance = distance;
    	targetSpeed = speed;
    	initialize();
    }

    protected void initialize() {
    	
    	complete = false;
    	currentSpeed = 0.2;
    	leftEncoderDistance = 0.0;
    	rightEncoderDistance = 0.0;
    	driveTrain.resetLeftEncoder();
    	driveTrain.resetRightEncoder();
    	driveTrain.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(currentSpeed < targetSpeed) {
    		currentSpeed += 0.095;
    	}
    	leftEncoderDistance = driveTrain.getLeftEncoderDistance();
    	rightEncoderDistance = driveTrain.getRightEncoderDistance();
    	
    	if (leftEncoderDistance <= myDistance && rightEncoderDistance <= myDistance) {
    		double angle = driveTrain.getGyroAngle(); // get current heading
    		driveTrain.autoDrive(currentSpeed, -(angle*Kp)); // drive towards heading 0
    		Timer.delay(0.04); 
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
