package org.usfirst.frc.team4041.robot.commands.teleop;

import org.usfirst.frc.team4041.robot.commands.teleop.CommandBase;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LiftUp extends CommandBase {

    public LiftUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires((Subsystem) lift);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	lift.up();

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	lift.stop();

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	lift.stop();

    }
}