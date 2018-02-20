package org.usfirst.frc.team4041.robot.commands.auto;

import org.usfirst.frc.team4041.robot.commands.teleop.CommandBase;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Auto_TurnToAngle extends CommandBase {
	
	private double targetAngle;
	private boolean complete;

    private Auto_TurnToAngle() {

    	requires((Subsystem) driveTrain);
    }
    
    public Auto_TurnToAngle(double angle) {
    	this();
    	targetAngle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	complete = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
      	double measuredAngle = driveTrain.getGyroAngle(); 
    	if(targetAngle >= 0) {
    		turnClockWise(measuredAngle);
    	}
    	else {
    		turnCounterClockWise(measuredAngle);
    	}
    }
    
    private void turnCounterClockWise(double measuredAngle) {
    	// turn left
    	if(measuredAngle > targetAngle){
    		driveTrain.tankDrive(-0.4,0.4);
    		Timer.delay(0.004);
    	}
    	else {
    		complete = true;
    	}
    }
    
    private void turnClockWise(double measuredAngle) {
    	//turn right
    	if(measuredAngle < targetAngle){
    		driveTrain.tankDrive(0.4,-0.4);
    		Timer.delay(0.004);
    	}
    	else {
    		complete = true;
    	}
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return complete;
    }

    // Called once after isFinished returns true
    protected void end() {
    	driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	driveTrain.stop();
    }
}
