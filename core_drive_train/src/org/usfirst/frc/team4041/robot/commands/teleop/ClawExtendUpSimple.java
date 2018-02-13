package org.usfirst.frc.team4041.robot.commands.teleop;

import org.usfirst.frc.team4041.robot.commands.teleop.CommandBase;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ClawExtendUpSimple extends CommandBase {
	

    public ClawExtendUpSimple() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires((Subsystem) clawExtend);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("initialize up command");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	clawExtend.up();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("up command end");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    	
    }
}
