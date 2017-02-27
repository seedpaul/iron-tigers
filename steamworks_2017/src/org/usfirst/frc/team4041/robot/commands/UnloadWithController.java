package org.usfirst.frc.team4041.robot.commands;

import edu.wpi.first.wpilibj.command.Subsystem;

public class UnloadWithController extends CommandBase {
	
	static final double pickerSpeed = -0.75;
	static final double unloaderSpeed = 0.65;
	static final double feederSpeed = -0.75;
	
	
    public UnloadWithController() {
        requires((Subsystem) picker);
        requires((Subsystem) waterfall);
        requires((Subsystem) feeder);
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	picker.startPicker(pickerSpeed);
    	waterfall.startWaterfall(unloaderSpeed);
    	feeder.startFeeder(feederSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	picker.stopPicker();
    	waterfall.stopWaterfall();
    	feeder.stopFeeder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	picker.stopPicker();
    	waterfall.stopWaterfall();
    	feeder.stopFeeder();
    }
}
