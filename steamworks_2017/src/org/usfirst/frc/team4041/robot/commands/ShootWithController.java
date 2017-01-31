package org.usfirst.frc.team4041.robot.commands;

import org.usfirst.frc.team4041.robot.OI;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ShootWithController extends CommandBase {
	
	static final double shooterSpeed = 0.5;
	static boolean runShooter;

    public ShootWithController() {
    	
        requires((Subsystem) shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	//we don't want it to run on initialization
    	runShooter = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(OI.isXButtonPressed()){
    		if(!runShooter){
    			shooter.runShooter(shooterSpeed);
    			runShooter = true;
    		}
    		else{
    			shooter.runShooter(0.0);
    			runShooter = false;
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
} 