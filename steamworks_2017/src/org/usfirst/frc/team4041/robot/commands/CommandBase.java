package org.usfirst.frc.team4041.robot.commands;

import org.usfirst.frc.team4041.robot.subsystems.*;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command 
{ 
    public static DriveTrain driveTrain;

    //initializes all of static variables
    public static void init() {

        driveTrain  = DriveTrain.getInstance();
    }

    //Constructor
    public CommandBase(String name) {
    	
        super(name);
    }

    //Default Constructor
    public CommandBase() {
    	
        super();
    }
}