package org.usfirst.frc.team4041.robot.commands;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimbWithController extends CommandBase {
	
	static final double climbingSpeed = -0.85;

    public ClimbWithController() {
    	
        requires((Subsystem) lifter);
        requires((Subsystem) camera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	lifter.startLifter(climbingSpeed);
    	camera.CameraFront();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	lifter.stopLifter();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	lifter.stopLifter();
    }
}
