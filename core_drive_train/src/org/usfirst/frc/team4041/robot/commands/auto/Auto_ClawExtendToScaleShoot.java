package org.usfirst.frc.team4041.robot.commands.auto;

import org.usfirst.frc.team4041.robot.commands.teleop.CommandBase;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Auto_ClawExtendToScaleShoot extends CommandBase {


    public Auto_ClawExtendToScaleShoot() {

    	requires((Subsystem) clawExtendPID);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	clawExtendPID.moveToScaleShoot();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return clawExtendPID.isComplete();
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {

    }
}
