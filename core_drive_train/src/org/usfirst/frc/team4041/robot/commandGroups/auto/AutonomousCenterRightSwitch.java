package org.usfirst.frc.team4041.robot.commandGroups.auto;

import org.usfirst.frc.team4041.robot.commands.auto.*;
import org.usfirst.frc.team4041.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCenterRightSwitch extends CommandGroup {

    public AutonomousCenterRightSwitch() {
    	
    	System.out.println("run auto center-right");

    	DriveTrain driveTrain  = DriveTrain.getInstance();
    	double speed = 0.40;
    	double angle = 50.0;
    	double default_timeout = 10.0;
    	double short_timeout = 1.0;
    	double super_short_timeout = .5;
    	
    	driveTrain.resetGyro();
    	driveTrain.resetLeftEncoder();
    	driveTrain.resetRightEncoder();
    	
    	//step:0
    	System.out.println("step: 0");
    	addSequential(new Auto_ClawExtendToVertical(),super_short_timeout);
    	//step :1
    	System.out.println("step: 1");
    	addSequential(new Auto_DriveStraight(12, speed), default_timeout);
    	//step :2
    	System.out.println("step: 2");
    	addSequential(new Auto_TurnToAngle(angle), default_timeout);
    	//step :3
    	System.out.println("step: 3");
    	addSequential(new Auto_DriveStraight(60,speed), default_timeout);
    	//step :4
    	System.out.println("step: 4");
    	addSequential(new Auto_TurnToAngle(-angle), default_timeout);
    	//step :5
    	System.out.println("step: 5");
    	addParallel(new Auto_ElevatorToSwitch(),short_timeout);
    	//step :6
    	System.out.println("step: 6");
    	addSequential(new Auto_DriveStraight(30, speed), 2.0);
    	//step :7
    	System.out.println("step: 7");
    	addSequential(new Auto_ClawExtendToHorizontal(),short_timeout);
    	//step :8
    	System.out.println("step: 8");
    	addSequential(new Auto_ClawIntakeShoot(),short_timeout);
    	
    	System.out.println("step: 9");
    	addSequential(new Auto_ClawIntakeStop(),short_timeout);
    	
    	// start second cube
    	System.out.println("step: 10");
    	addSequential(new Auto_DriveBack(50,speed),default_timeout);

    	System.out.println("step: 11");
    	addSequential(new Auto_TurnToAngle(-33.0), default_timeout);
    	
    	System.out.println("step: 12");
    	addParallel(new Auto_ElevatorToStarting(),short_timeout);
    	
      	System.out.println("step: 13");
     	addSequential(new Auto_ClawIntakeEngulf(),short_timeout);
     	
    	System.out.println("step: 14");
    	addSequential(new Auto_DriveStraight(58,speed), default_timeout);

    	System.out.println("step: 15");
    	addSequential(new Auto_DriveBack(37,speed),default_timeout);
    	
    	System.out.println("step: 16");
     	addSequential(new Auto_ClawIntakeSlow(),short_timeout);
 
    	System.out.println("step: 17");
    	addSequential(new Auto_TurnToAngle(43.0), default_timeout);
    	
    	System.out.println("step: 18");
    	addParallel(new Auto_ElevatorToSwitch(),short_timeout);

       	System.out.println("step: 19");
    	addSequential(new Auto_DriveStraight(68,speed), default_timeout);
    	
    	System.out.println("step: 20");
    	addSequential(new Auto_ClawIntakeShoot(),short_timeout);
    }
}


