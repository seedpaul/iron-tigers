package org.usfirst.frc.team4041.robot.commands.auto;

import org.usfirst.frc.team4041.robot.commands.teleop.CommandBase;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Auto_ElevatorToSwitch extends CommandBase {


    public Auto_ElevatorToSwitch() {

    	requires((Subsystem) elevatorPID);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//elevatorPID.resetEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	elevatorPID.upToSwitchHeight();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return elevatorPID.isComplete();
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {

    }
}
