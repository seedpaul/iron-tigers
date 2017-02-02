package org.usfirst.frc.team4041.robot.commands;

//import org.usfirst.frc.team4041.robot.OI;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ShootWithController extends CommandBase {
	
	static final double shooterSpeed = 0.45;

    public ShootWithController() {
    	
        requires((Subsystem) shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	//we don't want it to run on initialization
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
//    	if(OI.isBButtonPressed()){
//        	if(shooter.isSpinning()){
//        		shooter.stopShooter();
//        	}else{
        		shooter.runShooter(shooterSpeed);
//        	}
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	shooter.stopShooter();
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