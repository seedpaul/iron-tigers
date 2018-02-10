package org.usfirst.frc.team4041.robot.commands;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ElevatorDownWithController extends CommandBase {

    public ElevatorDownWithController() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires((Subsystem) elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("initialize elevator down command");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//elevator.down();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("elevator down command end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    	
    }
}
