package org.usfirst.frc.team4041.robot;

import org.usfirst.frc.team4041.robot.commandGroups.autonomous_center_right;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	static final Joystick xboxDriver = new Joystick(RobotMap.xboxControllerDriver);
	static final JoystickButton buttonA = new JoystickButton(xboxDriver, 1);
	
    public void init() {
    	
    	//start driver xbox controller buttons *********************************
    	//buttonA.toggleWhenPressed(new DriveStraight(60,0.5));
    	buttonA.toggleWhenPressed(new autonomous_center_right());

    	//end driver xbox controller buttons *********************************

    	//end assistant xbox controller buttons *********************************
    }
    
    private static double deadzone(double d) {
        //whenever the controller moves LESS than the magic number, the 
        //joy stick is in the loose position so return zero - as if the 
        //joy stick was not moved
        if (Math.abs(d) < RobotMap.DEADZONE_MAGIC_NUMBER) {
            return 0;
        }

        //When the joy stick is used for a purpose (passes the if statements, 
        //hence not just being loose), do math
        //gets the sign of d, negative or positive
        return (d / Math.abs(d)) * ((Math.abs(d) - RobotMap.DEADZONE_MAGIC_NUMBER) / (1 - RobotMap.DEADZONE_MAGIC_NUMBER)); //scales it
    }

    public static double getLeftStickY() {
        return deadzone(-xboxDriver.getRawAxis(RobotMap.leftStickY));
    }

    public static double getRightStickY() {
    	return deadzone(-xboxDriver.getRawAxis(RobotMap.rightStickY));
    }

    public static Joystick getDriveController() {
        return xboxDriver;
    }
}
