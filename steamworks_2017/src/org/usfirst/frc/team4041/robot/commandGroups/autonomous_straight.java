package org.usfirst.frc.team4041.robot.commandGroups;

import org.usfirst.frc.team4041.robot.commands.DriveStraight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class autonomous_straight extends CommandGroup {

    public autonomous_straight() {
       addSequential(new DriveStraight(5, 0.4));
    }
}
