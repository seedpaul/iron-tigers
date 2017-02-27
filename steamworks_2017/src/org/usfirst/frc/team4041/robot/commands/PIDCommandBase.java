package org.usfirst.frc.team4041.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;

import org.usfirst.frc.team4041.robot.subsystems.*;
import org.usfirst.frc.team4041.robot.OI;

public abstract class PIDCommandBase extends PIDCommand 
{ 
    public static DriveTrain driveTrain;
    public static Shooter shooter;
    public static Picker picker;
    public static Feeder feeder;
    public static Lifter lifter;
    public static Waterfall waterfall;
    
    public static OI oi;

    //initializes all of static variables
    public static void init() {

        driveTrain  = DriveTrain.getInstance();
        shooter = Shooter.getInstance();
        picker = Picker.getInstance();
        feeder = Feeder.getInstance();
        lifter = Lifter.getInstance();
        waterfall = Waterfall.getInstance();
        
        oi = new OI();
        oi.init();
    }

    //Constructor
    public PIDCommandBase(String name, double p,double i, double d, double period) {	
        super(name, p, i, d, period);
    }
}