package org.usfirst.frc.team4041.robot.commands;

import edu.wpi.first.wpilibj.command.Subsystem;

public class TurnRight extends CommandBase {
	
	private static double angle = 0;
	private static boolean finishedDriving = false; 
	
    public TurnRight() {
    	this(angle);
    }
	
	public TurnRight(double angle_in) {
		requires((Subsystem) driveTrain);
		angle = angle_in;
    }
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	//System.out.println("drive foward init");
    	driveTrain.getLeftEncoder().reset();
    	driveTrain.getRightEncoder().reset();
    	driveTrain.getGyro().reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println("drive foward execute");
    	finishedDriving = driveTrain.turnRight(angle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//System.out.println("drive foward isFinished");
        return finishedDriving;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//System.out.println("drive forward end");
    	driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//System.out.println("drive foward interrupted");
    	driveTrain.stop();
    }
    
}
