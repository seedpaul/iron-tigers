package org.usfirst.frc.team4041.robot.commandGroups.auto;

import org.usfirst.frc.team4041.robot.commands.auto.*;
import org.usfirst.frc.team4041.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCenterRightSwitch extends CommandGroup {

    public AutonomousCenterRightSwitch() {
    	
    	System.out.println("run auto center-right");

    	DriveTrain driveTrain  = DriveTrain.getInstance();
    	double speed = 0.45;
    	double angle = 45;
    	double default_timeout = 10;
    	double shoot_timeout = 2;
    	
    	driveTrain.resetGyro();
    	driveTrain.resetLeftEncoder();
    	driveTrain.resetRightEncoder();

    	//step :1
    	addSequential(new Auto_DriveStraight(12, speed), default_timeout);
    	//step :2
    	addSequential(new Auto_TurnToAngle(angle), default_timeout);
    	//step :3
    	addSequential(new Auto_DriveStraight(66,speed), default_timeout);
    	//step :4
    	addSequential(new Auto_TurnToAngle(-angle), default_timeout);
    	//step :5
    	addSequential(new Auto_ElevatorToSwitch(),default_timeout);
    	//step :6n
    	addSequential(new Auto_ClawExtendToHorizontal(),default_timeout);
    	//step :7
    	addSequential(new Auto_DriveStraight(42, speed), default_timeout);
    	//step :8
    	addSequential(new Auto_ClawIntakeShoot(),shoot_timeout);
    	
    }
}


