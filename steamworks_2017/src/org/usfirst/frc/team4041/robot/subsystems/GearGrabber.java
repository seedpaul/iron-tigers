package org.usfirst.frc.team4041.robot.subsystems;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;

public class GearGrabber extends Subsystem {

	static final Spark grabberSpark = new Spark(RobotMap.grabberSpark);;
	static final DigitalInput centerSwitch = new DigitalInput(RobotMap.centerSwitch);
	private static GearGrabber instance;
	
	private GearGrabber(){
		
		initialize();
	}
	
    public static GearGrabber getInstance() {
    	
        if (instance == null) {
            instance = new GearGrabber();
        }
        return instance;
    }
    
    public void initialize() {
    }
    
    public DigitalInput getSwitch(){
    	return centerSwitch;
    }
    
    public void startGrabber(double speed){
    	
    	grabberSpark.set(speed);
    }
    
    public void stopGrabber(){
    	
    	grabberSpark.set(RobotMap.STOP);
    }
   
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


