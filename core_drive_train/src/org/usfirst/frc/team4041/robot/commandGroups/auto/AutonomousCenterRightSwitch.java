package org.usfirst.frc.team4041.robot.commandGroups.auto;

import org.usfirst.frc.team4041.robot.commands.auto.Auto_DriveStraight;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_TurnToAngle;
import org.usfirst.frc.team4041.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class AutonomousCenterRightSwitch extends CommandGroup {

    public AutonomousCenterRightSwitch() {
    	
    	System.out.println("run auto center-right");

    	DriveTrain driveTrain  = DriveTrain.getInstance();
    	double speed = 0.45;
    	double angle = 45;
    	double timeout = 10;
    	
    	driveTrain.resetGyro();
    	driveTrain.resetLeftEncoder();
    	driveTrain.resetRightEncoder();

    	addSequential(new Auto_DriveStraight(12, speed), timeout);
    	addSequential(new Auto_TurnToAngle(angle), timeout);
    	addSequential(new Auto_DriveStraight(66,speed), timeout);
    	addSequential(new Auto_TurnToAngle(-angle), timeout);
    	addSequential(new Auto_DriveStraight(42, speed), timeout);
    	
    }
}


