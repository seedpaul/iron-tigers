package org.usfirst.frc.team4041.robot.commands.teleop;

import org.usfirst.frc.team4041.robot.commands.teleop.CommandBase;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ClawExtendUp extends CommandBase {
	
    public ClawExtendUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires((Subsystem) clawExtendPID);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	clawExtendPID.up();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	clawExtendPID.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	clawExtendPID.stop();
    	this.end();
    	
    }
}
