package org.usfirst.frc.team4041.robot.commandGroups.auto;

import org.usfirst.frc.team4041.robot.commands.auto.Auto_ClawExtendToScaleShoot;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_ClawExtendToVertical;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_ClawIntakeShoot;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_DriveStraight;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_ElevatorToScale;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_TurnToAngle;
import org.usfirst.frc.team4041.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousLeftRightScale extends CommandGroup {

    public AutonomousLeftRightScale() {

    	System.out.println("run auto left right scale");
    	
    	DriveTrain driveTrain  = DriveTrain.getInstance();
    	double speed = 0.35;
    	double smallAngle = -37;
    	double largeAngle = 68;
    	double default_timeout = 7;
    	double med_timeout = 4;
    	double short_timeout = 2;
    	
    	driveTrain.resetGyro();
    	driveTrain.resetLeftEncoder();
    	driveTrain.resetRightEncoder();
    	
    	//step0
    	addSequential(new Auto_ClawExtendToVertical(),short_timeout);
    	//Step 1 - straight
    	addSequential(new Auto_DriveStraight(175, speed), default_timeout);
    	//Step 2 - turn left
    	addSequential(new Auto_TurnToAngle(largeAngle), default_timeout);
    	//Step 3 - straight
    	addSequential(new Auto_DriveStraight(200, speed), default_timeout);
    	//Step 4 - turn right
    	addSequential(new Auto_TurnToAngle(smallAngle), default_timeout);
    	//Step 5 
    	addSequential(new Auto_ElevatorToScale(), med_timeout);
    	//Step 6
    	addSequential(new Auto_ClawExtendToScaleShoot(),short_timeout);
    	//Step 7
    	addSequential(new Auto_DriveStraight(24, speed), default_timeout);
    	//Step 8
    	addSequential(new Auto_ClawIntakeShoot(),short_timeout);

    }
}
