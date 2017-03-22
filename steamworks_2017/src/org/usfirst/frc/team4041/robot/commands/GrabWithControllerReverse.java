package org.usfirst.frc.team4041.robot.commands;

import edu.wpi.first.wpilibj.command.Subsystem;

public class GrabWithControllerReverse extends CommandBase {
	
	static double grabberSpeed = -0.5;

    public GrabWithControllerReverse() {
        requires((Subsystem) grabber);
    }
    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//if(!grabber.getSwitch().get()){
    		grabber.startGrabber(grabberSpeed);
    	//}
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	grabber.stopGrabber();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	grabber.stopGrabber();
    }
}
