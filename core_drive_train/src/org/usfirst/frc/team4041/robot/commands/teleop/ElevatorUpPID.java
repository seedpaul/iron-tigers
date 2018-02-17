package org.usfirst.frc.team4041.robot.commands.teleop;

import org.usfirst.frc.team4041.robot.commands.teleop.CommandBase;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ElevatorUpPID extends CommandBase {

    public ElevatorUpPID() {
    	requires((Subsystem) elevatorPID);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("initialize elevator up command");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//elevatorPID.up();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("elevator up command end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    	
    }
}
