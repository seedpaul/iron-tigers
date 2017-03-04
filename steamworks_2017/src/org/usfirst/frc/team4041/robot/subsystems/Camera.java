package org.usfirst.frc.team4041.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.Servo;

//import org.usfirst.frc.team4041.robot.RobotMap;

public class Camera extends Subsystem {

	static final Servo  servo = new Servo(RobotMap.cameraServo);
	private static Camera instance;
	
	private Camera(){
		
		initialize();
	}
	
    public static Camera getInstance() {
    	
        if (instance == null) {
            instance = new Camera();
        }
        return instance;
    }
    
    public void initialize() {
    	
    }
    
    public void CameraFront(){
    	servo.set(1.0);
    }
    
    public void CameraBack(){	
    	servo.set(0.0);
    }
  
    public double getAngle(){
    	return servo.getAngle();
    }
   
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
