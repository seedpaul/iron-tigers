package org.usfirst.frc.team4041.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Victor;

import org.usfirst.frc.team4041.robot.RobotMap;

public class Lifter extends Subsystem {

	static final Victor lifterVictor = new Victor(RobotMap.lifterVictor);
	private static Lifter instance;
	
	private Lifter(){
		
		initialize();
	}
	
    public static Lifter getInstance() {
    	
        if (instance == null) {
            instance = new Lifter();
        }
        return instance;
    }
    
    public void initialize() {
    	
    }
    
    public void startLifter(double speed){
    	
    	lifterVictor.set(speed);
    }
    
    public void stopLifter(){
    	
    	lifterVictor.set(RobotMap.STOP);
    }
   
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

