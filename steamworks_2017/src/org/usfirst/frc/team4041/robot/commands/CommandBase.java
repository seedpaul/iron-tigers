package org.usfirst.frc.team4041.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4041.robot.subsystems.*;
import org.usfirst.frc.team4041.robot.OI;


public abstract class CommandBase extends Command 
{ 
    public static DriveTrain driveTrain;
    public static Shooter shooter;
    
    public static OI oi;

    //initializes all of static variables
    public static void init() {

        driveTrain  = DriveTrain.getInstance();
        shooter = Shooter.getInstance();
        
        oi = new OI();
        oi.init();
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