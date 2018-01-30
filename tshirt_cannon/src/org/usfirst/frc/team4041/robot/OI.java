package org.usfirst.frc.team4041.robot;

import org.usfirst.frc.team4041.robot.commands.CannonDownWithController;
import org.usfirst.frc.team4041.robot.commands.CannonUpWithController;
import org.usfirst.frc.team4041.robot.commands.FireCannonWithController;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	
	static final Joystick xboxDriver = new Joystick(RobotMap.xboxController);

	static final JoystickButton buttonBumperRight = new JoystickButton(xboxDriver, RobotMap.buttonBumperRight);
	static final JoystickButton buttonY = new JoystickButton(xboxDriver, RobotMap.buttonY);
	static final JoystickButton buttonA = new JoystickButton(xboxDriver, RobotMap.buttonA);

	private static double governor = -0.75;
	
    public void init() {
    	buttonBumperRight.toggleWhenPressed(new FireCannonWithController());
    	buttonY.toggleWhenActive(new CannonUpWithController());
    	buttonA.toggleWhenActive(new CannonDownWithController());

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
