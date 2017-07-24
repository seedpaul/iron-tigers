package org.usfirst.frc.team4041.robot;

import org.usfirst.frc.team4041.robot.commandGroups.GrabAndPickUpWithController;
import org.usfirst.frc.team4041.robot.commands.ClimbWithController;
import org.usfirst.frc.team4041.robot.commands.FeedWithController;
import org.usfirst.frc.team4041.robot.commands.GrabWithControllerUp;
//import org.usfirst.frc.team4041.robot.commands.GrabWithControllerDown;
import org.usfirst.frc.team4041.robot.commands.PickUpWithController;
import org.usfirst.frc.team4041.robot.commands.ShootWithController;
import org.usfirst.frc.team4041.robot.commands.UnloadWithController;
import org.usfirst.frc.team4041.robot.commands.RotateCameraFoward;
import org.usfirst.frc.team4041.robot.commands.RotateCameraBack;
import org.usfirst.frc.team4041.robot.commands.EjectWithController;
import org.usfirst.frc.team4041.robot.commands.EngulfWithController;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	static final Joystick xboxDriver = new Joystick(RobotMap.xboxControllerDriver);
	static final JoystickButton XD_buttonA = new JoystickButton(xboxDriver, RobotMap.buttonA);
	static final JoystickButton XD_buttonB = new JoystickButton(xboxDriver, RobotMap.buttonB);
	static final JoystickButton XD_buttonX = new JoystickButton(xboxDriver, RobotMap.buttonX);
	static final JoystickButton XD_buttonY = new JoystickButton(xboxDriver, RobotMap.buttonY);
	static final JoystickButton XD_buttonStart = new JoystickButton(xboxDriver, RobotMap.buttonStart);
	static final JoystickButton XD_buttonSelect = new JoystickButton(xboxDriver, RobotMap.buttonSelect);
	static final JoystickButton XD_buttonRightBumper = new JoystickButton(xboxDriver, RobotMap.buttonBumperRight);
	static final JoystickButton XD_buttonLeftBumper = new JoystickButton(xboxDriver, RobotMap.buttonBumperLeft);
	static final JoystickButton XD_buttonRightTrigger = new JoystickButton(xboxDriver, RobotMap.rightTrigger);
	static final JoystickButton XD_buttonLeftTrigger = new JoystickButton(xboxDriver, RobotMap.leftTrigger);

	static final Joystick xboxAssistant = new Joystick(RobotMap.xboxControllerAssistant);
	static final JoystickButton XA_buttonA = new JoystickButton(xboxAssistant, RobotMap.buttonA);
	static final JoystickButton XA_buttonB = new JoystickButton(xboxAssistant, RobotMap.buttonB);
	static final JoystickButton XA_buttonX = new JoystickButton(xboxAssistant, RobotMap.buttonX);
	static final JoystickButton XA_buttonY = new JoystickButton(xboxAssistant, RobotMap.buttonY);
	static final JoystickButton XA_buttonStart = new JoystickButton(xboxAssistant, RobotMap.buttonStart);
	static final JoystickButton XA_buttonSelect = new JoystickButton(xboxAssistant, RobotMap.buttonSelect);
	static final JoystickButton XA_buttonRightBumper = new JoystickButton(xboxAssistant, RobotMap.buttonBumperRight);
	static final JoystickButton XA_buttonLeftBumper = new JoystickButton(xboxAssistant, RobotMap.buttonBumperLeft);
	static final JoystickButton XA_buttonRightTrigger = new JoystickButton(xboxAssistant, RobotMap.rightTrigger);
	static final JoystickButton XA_buttonLeftTrigger = new JoystickButton(xboxAssistant, RobotMap.leftTrigger);
	
    public void init() {
    	
    	//start driver xbox controller buttons *********************************
    	XD_buttonA.toggleWhenPressed(new PickUpWithController());
    	XD_buttonB.toggleWhenPressed(new UnloadWithController());
    	
    	XD_buttonLeftBumper.toggleWhenPressed(new FeedWithController());
    	XD_buttonRightBumper.toggleWhenPressed(new ClimbWithController());
    	XD_buttonSelect.toggleWhenPressed(new RotateCameraFoward());
    	XD_buttonStart.toggleWhenPressed(new RotateCameraBack());
    	//end driver xbox controller buttons *********************************
    	
    	//start assistant xbox controller buttons ***********************************
    	XA_buttonA.toggleWhenPressed(new GrabAndPickUpWithController());
    	XA_buttonY.toggleWhenPressed(new GrabWithControllerUp());
    	
    	XA_buttonX.toggleWhenPressed(new EngulfWithController());
    	XA_buttonB.toggleWhenPressed(new EjectWithController());
    	
    	XA_buttonStart.toggleWhenPressed(new ShootWithController());
    	XA_buttonRightBumper.toggleWhenPressed(new ClimbWithController());
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

    public boolean getRightTrigger() {
	double triggerValue = xboxDriver.getRawAxis(RobotMap.rightTrigger);
	if (triggerValue < 0) {
		return true;
	} else {
		return false;
	}
}

    public boolean getLeftTrigger() {
        double triggerValue = xboxDriver.getRawAxis(RobotMap.leftTrigger);
        if (triggerValue > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static Joystick getDriveController() {
        return xboxDriver;
    }
}
