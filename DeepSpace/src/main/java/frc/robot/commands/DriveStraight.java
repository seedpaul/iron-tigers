/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

public class DriveStraight extends CommandBase {
	private double myDistance;
	private double targetSpeed;
	private double currentSpeed;
	private double leftEncoderDistance;
	private double rightEncoderDistance;
	private boolean complete = false;
	private static double Kp = 0.095;
	private boolean initialLoop = true;

	private DriveStraight() {;
		// System.out.println("DriveStraight()");
    	requires(driveTrain);
    }
    
    public DriveStraight(double distance, double speed) {
		this();
		// System.out.println("DriveStraight with distance and speed");
    	myDistance = distance;
    	targetSpeed = speed;
    	initialize();
    }

    protected void initialize() {
		
		// System.out.println("initialize");
    	complete = false;
    	currentSpeed = 0.2;
    	leftEncoderDistance = 0.0;
    	rightEncoderDistance = 0.0;
    	driveTrain.resetLeftEncoder();
    	driveTrain.resetRightEncoder();
		driveTrain.resetAHRS();
		driveTrain.brake();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

		// System.out.println("execute");

		if(initialLoop){
			driveTrain.resetLeftEncoder();
			driveTrain.resetRightEncoder();
			initialLoop = !initialLoop;
		}
    	
    	if(currentSpeed < targetSpeed) {
    		currentSpeed += 0.095;
		}
		

    	leftEncoderDistance = driveTrain.getLeftEncoderDistance();
    	rightEncoderDistance = driveTrain.getRightEncoderDistance();
    	
    	if (leftEncoderDistance <= myDistance && rightEncoderDistance <= myDistance) {
			// System.out.println("not there yet");
    		double angle = driveTrain.getAHRSAngle(); // get current heading
    		driveTrain.autoDrive(currentSpeed, -(angle*Kp)); // drive towards heading 0
    		Timer.delay(0.04); 
    	} else {
			// System.out.println("We're there");
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