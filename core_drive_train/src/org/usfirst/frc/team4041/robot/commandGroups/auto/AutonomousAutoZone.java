package org.usfirst.frc.team4041.robot.commandGroups.auto;

import org.usfirst.frc.team4041.robot.commands.auto.Auto_ClawExtendToVertical;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_DriveStraight;
import org.usfirst.frc.team4041.robot.commands.auto.Auto_TurnToAngle;
import org.usfirst.frc.team4041.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousAutoZone extends CommandGroup {

    public AutonomousAutoZone() {
    	
    	System.out.println("run auto drive to zone");
    	
    	DriveTrain driveTrain  = DriveTrain.getInstance();
    	double speed = 0.45;
    	double timeout = 10;
    	double short_timeout = 2;
    	
    	driveTrain.resetGyro();
    	driveTrain.resetLeftEncoder();
    	driveTrain.resetRightEncoder();
    	
      	//step:0
    	addSequential(new Auto_ClawExtendToVertical(),short_timeout);
    	//step:1
    	addSequential(new Auto_DriveStraight(110, speed), timeout);
    	
    }
}
