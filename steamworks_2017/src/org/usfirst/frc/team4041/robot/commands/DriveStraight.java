package org.usfirst.frc.team4041.robot.commands;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveStraight extends CommandBase {
	
	private static double distance = 0;
	private static double speed = 0;
	private static boolean finishedDriving = false; 
	
    public DriveStraight() {
    	this(distance, speed);
    }
	
	public DriveStraight(double distance, double speed) {
		requires((Subsystem) driveTrain);
    }
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	//System.out.println("drive foward init");
    	driveTrain.getLeftEncoder().reset();
    	driveTrain.getRightEncoder().reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println("drive foward execute");
    	finishedDriving = driveTrain.driveStraight(0.5, 12.0, 0.055);
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