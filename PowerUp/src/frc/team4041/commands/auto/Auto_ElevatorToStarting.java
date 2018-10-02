package frc.team4041.commands.auto;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4041.commands.teleop.CommandBase;

public class Auto_ElevatorToStarting extends CommandBase {

    public Auto_ElevatorToStarting() {

    	requires((Subsystem) elevatorPID);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	//elevatorPID.resetEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	elevatorPID.moveToStartingHeight();
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
