package org.usfirst.frc.team4041.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4041.robot.subsystems.*;
import org.usfirst.frc.team4041.robot.OI;

public abstract class CommandBase extends Command 
{ 
    public static DriveTrain driveTrain;
    public static Elevator elevator;
    public static ClawExtend clawExtend;
    public static ClawIntake clawIntake;
    public static Lift lift;

    public static OI oi;

    //initializes all of static variables
    public static void init() {

        driveTrain  = DriveTrain.getInstance();
        elevator = Elevator.getInstance();
        clawExtend = ClawExtend.getInstance();
        clawIntake = ClawIntake.getInstance();
        lift = Lift.getInstance();
        
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
    
    public CommandBase(Double timeout){
    	
    	super(timeout);
    }
}