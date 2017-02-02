package org.usfirst.frc.team4041.robot.commands;

//import org.usfirst.frc.team4041.robot.OI;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimbWithController extends CommandBase {
	
	static final double climbingSpeed = 0.35;
	//static boolean running;

    public ClimbWithController() {
    	
        requires((Subsystem) lifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	//we don't want it to run on initialization
    	//running = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
//    	if(OI.isYButtonPressed()){
//        	if(running){
//        		lifter.stopLifter();
//        	}else{
        		lifter.startLifter(climbingSpeed);
//        	}
//        	running = !running;
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	lifter.stopLifter();
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	lifter.stopLifter();
    	//running = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	lifter.stopLifter();
    	///running = false;
    }
}
