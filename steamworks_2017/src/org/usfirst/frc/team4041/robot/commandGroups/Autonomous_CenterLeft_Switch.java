package org.usfirst.frc.team4041.robot.commandGroups;

import org.usfirst.frc.team4041.robot.commands.DriveStraight;
import org.usfirst.frc.team4041.robot.commands.FeedInAutonomous;
import org.usfirst.frc.team4041.robot.commands.ShootInAutonomous;
import org.usfirst.frc.team4041.robot.commands.TurnLeft;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous_CenterLeft_Switch extends CommandGroup {

    public Autonomous_CenterLeft_Switch(boolean shoot) {
    	
    	if(shoot){
    		addParallel(new ShootInAutonomous(), 15);
    	}
    	addSequential(new DriveStraight(4.5, 0.4));
    	addSequential(new TurnLeft(-30.0));
    	
    	if(shoot){
    		addSequential(new FeedInAutonomous(), 4.0);
    	}
    	
    	addSequential(new DriveStraight(1.8, 0.4));
    }
}
