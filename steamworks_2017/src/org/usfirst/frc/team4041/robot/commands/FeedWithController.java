package org.usfirst.frc.team4041.robot.commands;

import edu.wpi.first.wpilibj.command.Subsystem;

public class FeedWithController extends CommandBase {
	
	static final double feederSpeed = 0.4;
	static final double shooterSpeed = -0.3;

    public FeedWithController() {
    	
        requires((Subsystem) feeder);
        requires((Subsystem) shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() { }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	feeder.startFeeder(feederSpeed);
    	shooter.startShooter(shooterSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {	
    	feeder.stopFeeder();
    	shooter.stopShooter();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	feeder.stopFeeder();
    	shooter.stopShooter();
    }
}
