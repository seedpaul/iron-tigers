package org.usfirst.frc.team4041.robot.commands.teleop;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4041.robot.subsystems.*;
import org.usfirst.frc.team4041.robot.subsystems.PID.*;

import org.usfirst.frc.team4041.robot.OI;

public abstract class CommandBase extends Command 
{ 

    public static DriveTrain driveTrain;
    public static ElevatorPID elevatorPID;
    public static ClawExtendPID clawExtendPID;
    public static ClawIntake clawIntake;
    public static ClimbingLift lift;

    public static OI oi;

    //initializes all of static variables
    public static void init() {

        driveTrain  = DriveTrain.getInstance();
        elevatorPID = ElevatorPID.getInstance();
        clawExtendPID = ClawExtendPID.getInstance();
        clawIntake = ClawIntake.getInstance();
        lift = ClimbingLift.getInstance();
        
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