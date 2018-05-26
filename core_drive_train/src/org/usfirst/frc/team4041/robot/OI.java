package org.usfirst.frc.team4041.robot;

import org.usfirst.frc.team4041.robot.commands.teleop.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class OI {

	static final Joystick xboxDriver = new Joystick(RobotMap.xboxControllerDriver);
	
	static final JoystickButton buttonB = new JoystickButton(xboxDriver, RobotMap.buttonB);
	static final JoystickButton buttonX = new JoystickButton(xboxDriver, RobotMap.buttonX);
	
    public void init() {
    	
    	Command startFlying = new StartFlying();
    	Command stopFlying = new StopFlying();
    	
    	buttonB.whenPressed(startFlying);
    	buttonX.whenPressed(stopFlying);
    }

    public static Joystick getDriverController() {
        return xboxDriver;
    }
}
