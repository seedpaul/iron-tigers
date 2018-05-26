package org.usfirst.frc.team4041.robot.commandGroups.auto;

import org.usfirst.frc.team4041.robot.commands.auto.Auto_ClawExtendToHorizontal;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_ClawExtendToVertical;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_ClawIntakeEngulf;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_ClawIntakeShoot;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_ClawIntakeSlow;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_ClawIntakeStop;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_DriveBack;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_DriveStraight;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_ElevatorToStarting;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_ElevatorToSwitch;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_TurnToAngle;
import org.usfirst.frc.team4041.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCenterLeftSwitch extends CommandGroup {
	
    public AutonomousCenterLeftSwitch() {
    	
    	System.out.println("run auto center-left with parallel");

    	DriveTrain driveTrain  = DriveTrain.getInstance();
    	double speed = 0.50;
    	double angle = -40.0;
    	double default_timeout = 5.0;
    	double short_timeout = 1.0;
    	double super_short_timeout = .5;
    	
    	driveTrain.resetGyro();
    	driveTrain.resetLeftEncoder();
    	driveTrain.resetRightEncoder();

    	System.out.println("step: 0 ");
    	addSequential(new Auto_ClawExtendToVertical(),super_short_timeout);

    	System.out.println("step: 1");
    	addSequential(new Auto_DriveStraight(12, speed), default_timeout);

    	System.out.println("step: 2");
    	addSequential(new Auto_TurnToAngle(angle), default_timeout);

    	System.out.println("step: 3");
    	addSequential(new Auto_DriveStraight(65,speed), default_timeout);

    	System.out.println("step: 4");
    	addSequential(new Auto_TurnToAngle(45), default_timeout);

    	System.out.println("step: 5");
    	addParallel(new Auto_ElevatorToSwitch(),short_timeout);

    	System.out.println("step: 6");
    	addSequential(new Auto_DriveStraight(7, speed), 2.0);

    	System.out.println("step: 7");
    	addSequential(new Auto_ClawExtendToHorizontal(),short_timeout);

    	System.out.println("step: 8");
    	addSequential(new Auto_ClawIntakeShoot(),super_short_timeout);
    	
    	System.out.println("step: 9");
    	addSequential(new Auto_ClawIntakeStop(),short_timeout);
    	
    	//start get second cube
    	
    	System.out.println("step: 10");
    	addSequential(new Auto_DriveBack(38,speed),default_timeout);

    	System.out.println("step: 11");
    	addSequential(new Auto_TurnToAngle(40.0), default_timeout);
    	
      	System.out.println("step: 12");
      	addParallel(new Auto_ElevatorToStarting(),short_timeout);
    	
     	System.out.println("step: 13");
     	addSequential(new Auto_ClawIntakeEngulf(),short_timeout);

    	System.out.println("step: 14");
    	addSequential(new Auto_DriveStraight(49,speed), default_timeout);

    	System.out.println("step: 15");
    	addSequential(new Auto_DriveBack(15,speed),default_timeout);
    	
     	System.out.println("step: 16");
     	addSequential(new Auto_ClawIntakeSlow(),short_timeout);
    	
    	System.out.println("step: 17");
    	addSequential(new Auto_TurnToAngle(-40.0), default_timeout);
    	
    	System.out.println("step: 18");
    	addParallel(new Auto_ElevatorToSwitch(),short_timeout);
    	
       	System.out.println("step: 19");
    	addSequential(new Auto_DriveStraight(63,speed), default_timeout);
    	
    	System.out.println("step: 20");
    	addSequential(new Auto_ClawIntakeShoot(),short_timeout);
    	
    }
}


