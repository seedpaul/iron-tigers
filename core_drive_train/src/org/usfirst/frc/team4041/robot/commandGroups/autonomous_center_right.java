package org.usfirst.frc.team4041.robot.commandGroups;

import org.usfirst.frc.team4041.robot.commands.DriveStraight;
import org.usfirst.frc.team4041.robot.commands.TurnToAngle;
import org.usfirst.frc.team4041.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class autonomous_center_right extends CommandGroup {

    public autonomous_center_right() {

    	DriveTrain driveTrain  = DriveTrain.getInstance();
    	double speed = 0.5;
    	double angle = 35;
    	double timeout = 10;
    	
    	driveTrain.resetGyro();
    	driveTrain.resetLeftEncoder();
    	driveTrain.resetRightEncoder();

    	addSequential(new DriveStraight(18, speed), timeout);
    	addSequential(new TurnToAngle(angle), timeout);
    	addSequential(new DriveStraight(54,speed), timeout);
    	addSequential(new TurnToAngle(-angle), timeout);
    	addSequential(new DriveStraight(24, speed), timeout);
    }
}


