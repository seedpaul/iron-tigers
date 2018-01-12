package org.usfirst.frc.team4041.robot;

import org.usfirst.frc.team4041.robot.commands.waveArm;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class OI {
	
	static final Joystick xboxDriver = new Joystick(RobotMap.xboxController);

	static final JoystickButton buttonRightTrigger = new JoystickButton(xboxDriver, RobotMap.rightTrigger_generic);

	private static double governor = -0.75;
	
    public void init() {
    	
    	buttonRightTrigger.toggleWhenPressed(new waveArm());

    }
    
    private static double deadzone(double d) {

        if (Math.abs(d) < RobotMap.DEADZONE_MAGIC_NUMBER) {
            return 0;
        }

        return (d / Math.abs(d)) * ((Math.abs(d) - RobotMap.DEADZONE_MAGIC_NUMBER) / (1 - RobotMap.DEADZONE_MAGIC_NUMBER)); //scales it
    }
    
    public static double getLeftStickY() {
    	double LSY = xboxDriver.getRawAxis(RobotMap.leftStickY);
    	return deadzone(governor * LSY);
    }

    public static double getRightStickY() {
    	double RSY = xboxDriver.getRawAxis(RobotMap.rightStickY);
    	return deadzone(governor * RSY);
    }

}
