package org.usfirst.frc.team4041.robot.commands;

import org.usfirst.frc.team4041.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Subsystem;

public class  DriveStraighter extends PIDCommandBase{

	private double distance; 
	private double maxspeed;
	
	public final static double kp = 0.078;
	public final static double ki = 0;
	public final static double kd = 0.6;
	public final static double max = 0.8;
	public final static double period = 0.01;
	
	public DriveStraighter(double distance, double p, double i, double d, double max ) {	
		super("a_drive_move_dist_straight", p, i, d, period);
		this.distance = distance;
		this.maxspeed = max;
		getPIDController().setContinuous(false);
        getPIDController().setAbsoluteTolerance(0.2);
        
        DriveTrain driveTrain = DriveTrain.getInstance();
        requires((Subsystem) driveTrain);
	}
    
    public DriveStraighter(double distance, double maxspeed) {
    	this(distance, kp, ki, kd, maxspeed);
    }
    
    public DriveStraighter(double distance) {
    	this(distance, max);
    }
    
    protected double returnPIDInput() {

        return driveTrain.getRightEncoder().pidGet();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	output = constrain(-maxspeed, output, maxspeed);
    	if (Math.abs(output) < .4 && output != 0) {
    		output = (output/Math.abs(output))*.4;
    	}
    	if ((timeSinceInitialized()+.4)<1) {
    		 output = output * (timeSinceInitialized()+.4);
    	}
    		
    	//driveTrain.driveStraighter(output);
    }
    
    private double constrain(double min, double value, double max){
    	if(value > max){
    		return max;
    	}
    	else if(value < min){
    		return min;
    	}
    	else{
    		return value;
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(driveTrain == null){
    		driveTrain = DriveTrain.getInstance();
    	}
    	
    	driveTrain.getRightEncoder().reset();
    	driveTrain.resetGyro();
    	getPIDController().setSetpoint(distance);
    	getPIDController().enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(getPIDController().getError()) <= 1.5;// && RobotMap.driveLeftEncoder.getRate() < .5
    }

    // Called once after isFinished returns true
    protected void end() {
    	getPIDController().disable();
    	driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	driveTrain.stop();
    	end();
    }
}
