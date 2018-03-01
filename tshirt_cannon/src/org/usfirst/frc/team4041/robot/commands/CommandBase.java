package org.usfirst.frc.team4041.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4041.robot.subsystems.*;
import org.usfirst.frc.team4041.robot.OI;

public abstract class CommandBase extends Command 
{ 
    public static DriveTrain driveTrain;
    public static Cannon cannon;
    public static Angler angler;
    public static Speaker speaker;
    
    public static OI oi;

    //initializes all of static variables
    public static void init() {

        driveTrain  = DriveTrain.getInstance();
        cannon = Cannon.getInstance();
        angler = Angler.getInstance();
        speaker = Speaker.getInstance();

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