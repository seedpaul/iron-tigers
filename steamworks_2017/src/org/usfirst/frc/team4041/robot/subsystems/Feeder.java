package org.usfirst.frc.team4041.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Victor;

import org.usfirst.frc.team4041.robot.RobotMap;

public class Feeder extends Subsystem {

	static final Victor feederVictor = new Victor(RobotMap.feederVictor);
	private static Feeder instance;
	
    private Feeder() {
    	// do some stuff here it need be!
    	initialize();
    }
	
    public static Feeder getInstance() {
    	
        if (instance == null) {
            instance = new Feeder();
        }
        return instance;
    }
    
    public void initialize() {
    	
    }
    
    public void startFeeder(double speed){
    	
    	feederVictor.set(speed);
    }
    
    public void stopFeeder(){
    	
    	feederVictor.set(RobotMap.STOP);
    }
   
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}