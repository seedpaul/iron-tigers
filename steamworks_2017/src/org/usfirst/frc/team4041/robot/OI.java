package org.usfirst.frc.team4041.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	static final Joystick xbox = new Joystick(RobotMap.xboxController);
	static final JoystickButton buttonA = new JoystickButton(xbox, RobotMap.buttonA);
	static final JoystickButton buttonB = new JoystickButton(xbox, RobotMap.buttonB);
	static final JoystickButton buttonX = new JoystickButton(xbox, RobotMap.buttonX);
	static final JoystickButton buttonY = new JoystickButton(xbox, RobotMap.buttonY);
	static final JoystickButton buttonRightBumper = new JoystickButton(xbox, RobotMap.buttonBumperRight);
	static final JoystickButton buttonLeftBumper = new JoystickButton(xbox, RobotMap.buttonBumperLeft);

    public void init() {
    	//put some code here if you like
    }

    private static double deadzone(double d) {
        //whenever the controller moves LESS than the magic number, the 
        //joystick is in the loose position so return zero - as if the 
        //joystick was not moved
        if (Math.abs(d) < RobotMap.DEADZONE_MAGIC_NUMBER) {
            return 0;
        }

        //When the joystick is used for a purpose (passes the if statements, 
        //hence not just being loose), do math
        //gets the sign of d, negative or positive
        return (d / Math.abs(d)) * ((Math.abs(d) - RobotMap.DEADZONE_MAGIC_NUMBER) / (1 - RobotMap.DEADZONE_MAGIC_NUMBER)); //scales it
    }

    public static double getLeftStickY() {
        return deadzone(-xbox.getRawAxis(RobotMap.leftStickY));
    }

    public static double getRightStickY() {
        return deadzone(-xbox.getRawAxis(RobotMap.rightStickY));
    }

    public static boolean isXButtonPressed() {
        return xbox.getRawButton(RobotMap.buttonX);
    }

    public static boolean isYButtonPressed() {
        return xbox.getRawButton(RobotMap.buttonY);
    }

    public static boolean isAButtonPressed() {
        return xbox.getRawButton(RobotMap.buttonA);
    }

    public static boolean isBButtonPressed() {
        return xbox.getRawButton(RobotMap.buttonB);
    }

    public static boolean isRBButtonPressed() {
        return xbox.getRawButton(RobotMap.buttonBumperRight);
    }

    public static boolean isLBButtonPressed() {
        return xbox.getRawButton(RobotMap.buttonBumperLeft);
    }

    public static double getRightTrigger() {
        double triggerValue = xbox.getRawAxis(RobotMap.rightTrigger);
        if (triggerValue < 0) {
            return Math.abs(deadzone(triggerValue));
        } else {
            return 0;
        }
    }

    public static double getLeftTrigger() {
        double triggerValue = xbox.getRawAxis(RobotMap.leftTrigger);
        if (triggerValue > 0) {
            return deadzone(triggerValue);
        } else {
            return 0;
        }
    }

}
