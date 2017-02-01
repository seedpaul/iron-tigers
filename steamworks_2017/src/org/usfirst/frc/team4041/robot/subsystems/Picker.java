package org.usfirst.frc.team4041.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;

import org.usfirst.frc.team4041.robot.RobotMap;

public class Picker extends Subsystem {

	static final VictorSP pickerVictor = new VictorSP(RobotMap.pickerVictor);
	private static Picker instance;
	
	private Picker(){
		
		initialize();
	}
	
    public static Picker getInstance() {
    	
        if (instance == null) {
            instance = new Picker();
        }
        return instance;
    }
    
    public void initialize() {
    	
    }
    
    public void startPicker(double speed){
    	
    	pickerVictor.set(speed);
    }
    
    public void stopPicker(){
    	
    	pickerVictor.set(RobotMap.STOP);
    }
   
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
