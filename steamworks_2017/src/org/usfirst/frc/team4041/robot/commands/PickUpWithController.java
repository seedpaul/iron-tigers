package org.usfirst.frc.team4041.robot.commands;

import edu.wpi.first.wpilibj.command.Subsystem;

public class PickUpWithController extends CommandBase {
	
	static final double pickerSpeed = 0.75;
	//static boolean running;

    public PickUpWithController() {
    	
        requires((Subsystem) picker);
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	picker.startPicker(pickerSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	picker.stopPicker();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	picker.stopPicker();

    }
}