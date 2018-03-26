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
    	double speed = 0.4;
    	double smallAngle = -103;
    	double largeAngle = 90;
    	double default_timeout = 7;
    	double med_timeout = 6;
    	double short_timeout = 2;
    	double super_short_timeout = .5;
    	
    	driveTrain.resetGyro();
    	driveTrain.resetLeftEncoder();
    	driveTrain.resetRightEncoder();
    	
    	//Step 0
    	addSequential(new Auto_ClawExtendToVertical(),super_short_timeout);
    	//Step 1 - straight
    	addSequential(new Auto_DriveStraight(200, speed), default_timeout);
    	//Step 2 - turn left
    	addSequential(new Auto_TurnToAngle(largeAngle), default_timeout);
    	//Step 3 - straight
    	addSequential(new Auto_DriveStraight(160, speed), default_timeout);
    	//Step 4 - turn right
    	//addSequential(new Auto_TurnToAngle(smallAngle), default_timeout);
    	//Step 5 
    	addSequential(new Auto_ClawExtendToScaleShoot(),short_timeout);
    	//Step 6
    	//addSequential(new Auto_ElevatorToScale(), med_timeout);
    	//Step 7
    	//addSequential(new Auto_DriveStraight(35, speed), default_timeout);
    	//Step 8
    	//addSequential(new Auto_ClawIntakeShoot(),short_timeout);

    }
}
