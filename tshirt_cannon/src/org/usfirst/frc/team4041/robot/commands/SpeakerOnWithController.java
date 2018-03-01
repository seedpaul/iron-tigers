package org.usfirst.frc.team4041.robot.commands;


import edu.wpi.first.wpilibj.command.Subsystem;

public class SpeakerOnWithController extends CommandBase {
	
    public SpeakerOnWithController(){
    	
		requires((Subsystem) speaker);
    }

    // Called just before this Command runs the first time
    protected void initialize(){
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {   
    	speaker.on();
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished(){
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end(){
    	speaker.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        //log("I've been interrupted and am differing to the new Command");
    	speaker.stop();
    }

}
