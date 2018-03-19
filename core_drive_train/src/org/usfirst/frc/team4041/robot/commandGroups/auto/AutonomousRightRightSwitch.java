package org.usfirst.frc.team4041.robot.commandGroups.auto;

import org.usfirst.frc.team4041.robot.commands.auto.Auto_ClawExtendToHorizontal;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_ClawExtendToVertical;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_ClawIntakeShoot;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_DriveStraight;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_ElevatorToSwitch;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_TurnToAngle;
import org.usfirst.frc.team4041.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousRightRightSwitch extends CommandGroup {

    public AutonomousRightRightSwitch() {
    	
System.out.println("run auto right right switch");
    	
    	DriveTrain driveTrain  = DriveTrain.getInstance();
    	double speed = 0.45;
    	double angle = -68;
    	double default_timeout = 7;
    	double short_timeout = 2;
    	double super_short_timeout = .5;
    	
    	driveTrain.resetGyro();
    	driveTrain.resetLeftEncoder();
    	driveTrain.resetRightEncoder();

    	//STep 0
    	addSequential(new Auto_ClawExtendToVertical(),super_short_timeout);
    	//STep2
    	addSequential(new Auto_DriveStraight(125, speed), default_timeout);
    	//Step 3
    	addSequential(new Auto_TurnToAngle(angle), default_timeout);
    	//Step 4
    	addSequential(new Auto_ElevatorToSwitch(), default_timeout);
    	//Step 5
    	addSequential(new Auto_DriveStraight(8, speed), default_timeout);
    	//Step 6
    	addSequential(new Auto_ClawExtendToHorizontal(),short_timeout);
    	//Step 7
    	addSequential(new Auto_ClawIntakeShoot(),short_timeout);
    }
}
