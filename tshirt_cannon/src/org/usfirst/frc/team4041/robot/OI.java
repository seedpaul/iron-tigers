package org.usfirst.frc.team4041.robot;


import org.usfirst.frc.team4041.robot.commands.CannonDownWithController;
import org.usfirst.frc.team4041.robot.commands.CannonUpWithController;
import org.usfirst.frc.team4041.robot.commands.FireCannonWithController;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	
	static final Joystick xboxDriver = new Joystick(RobotMap.xboxController);

	static final JoystickButton buttonRightTrigger = new JoystickButton(xboxDriver, RobotMap.rightTrigger_generic);
	static final JoystickButton button4 = new JoystickButton(xboxDriver, RobotMap.button2_generic);
	static final JoystickButton button2 = new JoystickButton(xboxDriver, RobotMap.button4_generic);

	private static double governor = -0.75;
	
    public void init() {
    	
    	buttonRightTrigger.whenPressed(new FireCannonWithController());
    	button4.whileHeld(new CannonUpWithController());
    	button2.whileHeld(new CannonDownWithController());
    }
    
    private static double deadzone(double d) {

        if (Math.abs(d) < RobotMap.DEADZONE_MAGIC_NUMBER) {
            return 0;
        }

        return (d / Math.abs(d)) * ((Math.abs(d) - RobotMap.DEADZONE_MAGIC_NUMBER) / (1 - RobotMap.DEADZONE_MAGIC_NUMBER)); //scales it
    }
    
    public static double getLeftStickY() {
    	double LSY = xboxDriver.getRawAxis(RobotMap.leftStickY_generic);
    	return deadzone(governor * LSY);
    }

    public static double getRightStickY() {
    	double RSY = xboxDriver.getRawAxis(RobotMap.rightStickY_generic);
    	return deadzone(governor * RSY);
    }

}
