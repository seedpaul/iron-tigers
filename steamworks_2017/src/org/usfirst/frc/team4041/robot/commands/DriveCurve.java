package org.usfirst.frc.team4041.robot.commands;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveCurve extends CommandBase {
	
	private static double radius;
	private static double wheelbase;
	private static double distance;
	private static double magnitude;
	
	double curve;
	
	private static boolean finishedDriving = false; 
	
    public DriveCurve() {
    	// some random default values we used during testing
    	this(24.0, 12.0, 0.4, 10.0);
    }
    
	public DriveCurve(double Radius, double Wheelbase, double Magnitude, double Distance) {
		requires((Subsystem) driveTrain);
		radius = Radius;
		wheelbase = Wheelbase;
		magnitude = Magnitude;
		distance = Distance;
    }
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	//System.out.println("drive foward init");
    	driveTrain.getLeftEncoder().reset();
    	driveTrain.getRightEncoder().reset();
    
    	curve = Math.exp((-radius)/wheelbase);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("curve: " + curve);
    	finishedDriving = driveTrain.driveCurve(magnitude, curve, distance);
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
