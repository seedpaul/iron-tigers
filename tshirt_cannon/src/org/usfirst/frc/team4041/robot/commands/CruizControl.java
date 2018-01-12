package org.usfirst.frc.team4041.robot.commands;

import org.usfirst.frc.team4041.robot.OI;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CruizControl extends CommandBase {
	
	private static double left;
	private static double right;

	 public CruizControl(){
	    	
		 requires((Subsystem) driveTrain);

	    }

	    // Called just before this Command runs the first time
	    protected void initialize(){
	    	left = OI.getLeftStickY();
	    	right = OI.getRightStickY();
	    	
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {   
	    	System.out.println("left:" + left);
	    	System.out.println("right:" + right);
	    	 driveTrain.tankDrive(left , right);
	    }
	    
	    // Make this return true when this Command no longer needs to run execute()
	    protected boolean isFinished(){
	    	
	        return false;
	    }

	    // Called once after isFinished returns true
	    protected void end(){
	    	driveTrain.stop();
	    }

	    // Called when another command which requires one or more of the same
	    // subsystems is scheduled to run
	    protected void interrupted() {
	        //log("I've been interrupted and am differing to the new Command");
	    	driveTrain.stop();
	    }

}
