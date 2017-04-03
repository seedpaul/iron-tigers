package org.usfirst.frc.team4041.robot.commands;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ShootInAutonomous extends CommandBase {
	
	static final double shooterSpeed = 0.50;
	static final double thresholdLow = 550.0;
	static final double thresholdHigh = 575.0;

    public ShootInAutonomous() {
    	
        requires((Subsystem) shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    	//we don't want it to run on initialization
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooter.startShooter(shooterSpeed);
   		if (shooter.getShooterSpeed() < thresholdLow){
   			shooter.startShooter(shooterSpeed + 0.10);
   		}
   		else if (shooter.getShooterSpeed() > thresholdHigh){
   			shooter.startShooter(shooterSpeed - 0.10);
   		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooter.stopShooter();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	shooter.stopShooter();
    }
} 