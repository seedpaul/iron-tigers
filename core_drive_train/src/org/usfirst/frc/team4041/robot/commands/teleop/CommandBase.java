package org.usfirst.frc.team4041.robot.commands.teleop;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4041.robot.subsystems.*;
//import org.usfirst.frc.team4041.robot.subsystems.PID.ClawExtendPID;
//import org.usfirst.frc.team4041.robot.subsystems.PID.DriveTrainPID;
//import org.usfirst.frc.team4041.robot.subsystems.PID.ElevatorPID;
import org.usfirst.frc.team4041.robot.OI;

public abstract class CommandBase extends Command 
{ 
    //public static DriveTrainPID driveTrainPID;
    public static DriveTrain driveTrain;
    public static Elevator elevator;
    //public static ElevatorPID elevatorPID;
    public static ClawExtend clawExtend;
    //public static ClawExtendPID clawExtendPID;
    public static ClawIntake clawIntake;
    public static ClimbingLift lift;

    public static OI oi;

    //initializes all of static variables
    public static void init() {

        driveTrain  = DriveTrain.getInstance();
        //driveTrainPID  = DriveTrainPID.getInstance();
        elevator = Elevator.getInstance();
        //elevatorPID = ElevatorPID.getInstance();
        clawExtend = ClawExtend.getInstance();
        //clawExtendPID = ClawExtendPID.getInstance();
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