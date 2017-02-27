package org.usfirst.frc.team4041.robot.commands;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ShootWithController extends CommandBase {
	
	static final double shooterSpeed = 0.50;
	static final double feederSpeed = -0.75;
	static final double waterfallSpeed = -0.35;
	static final double thresholdLow = 550.0;
	static final double thresholdHigh = 575.0;

    public ShootWithController() {
    	
        requires((Subsystem) shooter);
        requires((Subsystem) feeder);
        requires((Subsystem) waterfall);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    	//we don't want it to run on initialization
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shooter.startShooter(shooterSpeed);
   		if(shooter.getShooterSpeed() > thresholdLow && shooter.getShooterSpeed() < thresholdHigh){
   			feeder.startFeeder(feederSpeed);
   			waterfall.startWaterfall(waterfallSpeed);
   		}
   		else if (shooter.getShooterSpeed() < thresholdLow){
   			shooter.startShooter(shooterSpeed + 0.10);
   			feeder.stopFeeder();
   			waterfall.startWaterfall(waterfallSpeed);
   		}
   		else if (shooter.getShooterSpeed() > thresholdHigh){
   			shooter.startShooter(shooterSpeed - 0.10);
   			feeder.stopFeeder();
   			waterfall.startWaterfall(waterfallSpeed);
   		}
   		else{
   			feeder.stopFeeder();
   			waterfall.startWaterfall(waterfallSpeed);
   		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shooter.stopShooter();
    	feeder.stopFeeder();
    	waterfall.stopWaterfall();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	shooter.stopShooter();
    	feeder.stopFeeder();
    	waterfall.stopWaterfall();
    }
} 