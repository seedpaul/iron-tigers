package org.usfirst.frc.team4041.robot.commandGroups.auto;

import org.usfirst.frc.team4041.robot.commands.auto.Auto_ClawExtendToHorizontal;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_ClawIntakeShoot;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_DriveStraight;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_ElevatorToScale;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_TurnToAngle;
import org.usfirst.frc.team4041.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousLeftLeftScale extends CommandGroup {

    public AutonomousLeftLeftScale() {
    	//ToDo: Change to fit sequence
    	DriveTrain driveTrain  = DriveTrain.getInstance();
    	double speed = 0.45;
    	double angle = 45;
    	double default_timeout = 10;
    	double shoot_timeout = 2;
    	
    	driveTrain.resetGyro();
    	driveTrain.resetLeftEncoder();
    	driveTrain.resetRightEncoder();

    	//Step 1
    	addSequential(new Auto_DriveStraight(12, speed), default_timeout);
    	//Step 2
    	addSequential(new Auto_TurnToAngle(angle), default_timeout);
    	//Step 3
    	addSequential(new Auto_ElevatorToScale(), default_timeout);
    	//Step 4
    	addSequential(new Auto_ClawExtendToHorizontal(),default_timeout);
    	//Step 5
    	addSequential(new Auto_DriveStraight(42, speed), default_timeout);
    	//Step 6
    	addSequential(new Auto_ClawIntakeShoot(),shoot_timeout);
    	
    	System.out.println("run auto left scale");
    }
}
