package org.usfirst.frc.team4041.robot.commandGroups.auto;

import org.usfirst.frc.team4041.robot.commands.auto.*;
import org.usfirst.frc.team4041.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousRightRightScale extends CommandGroup {

    public AutonomousRightRightScale() {
    	
    	System.out.println("run auto right right scale");
    	
    	DriveTrain driveTrain  = DriveTrain.getInstance();
    	double speed = 0.35;
    	double angle = -37;
    	double default_timeout = 7;
    	double med_timeout = 4;
    	double short_timeout = 2;
    	
    	driveTrain.resetGyro();
    	driveTrain.resetLeftEncoder();
    	driveTrain.resetRightEncoder();
    	

    	//step0
    	addSequential(new Auto_ClawExtendToVertical(),1);
    	//Step 1
    	addSequential(new Auto_DriveStraight(239, speed), default_timeout);
    	//Step 2
    	addSequential(new Auto_TurnToAngle(angle), default_timeout);
    	//Step 3
    	//addSequential(new Auto_DriveBack(-10, speed), default_timeout);
    	//Step 4
    	addSequential(new Auto_ClawExtendToScaleShoot(),short_timeout);
    	//Step 4
    	addSequential(new Auto_ElevatorToScale(), med_timeout);
    	//Step 5
    	//addSequential(new Auto_DriveStraight(24, speed), default_timeout);
    	//Step 6
    	addSequential(new Auto_ClawIntakeShoot(),short_timeout);

    }
}
