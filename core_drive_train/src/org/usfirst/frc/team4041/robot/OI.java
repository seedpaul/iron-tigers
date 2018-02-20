package org.usfirst.frc.team4041.robot;

import org.usfirst.frc.team4041.robot.commands.teleop.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class OI {

	static final Joystick xboxDriver = new Joystick(RobotMap.xboxControllerDriver);
	
	static final JoystickButton buttonB_dr = new JoystickButton(xboxDriver, RobotMap.buttonB);
	static final JoystickButton buttonX_dr = new JoystickButton(xboxDriver, RobotMap.buttonX);
	
	static final JoystickButton buttonBumperLeft_dr = new JoystickButton(xboxDriver, RobotMap.buttonBumperLeft);
	static final JoystickButton buttonBumperRight_dr = new JoystickButton(xboxDriver, RobotMap.buttonBumperRight);
	
	static final JoystickButton buttonY_dr = new JoystickButton(xboxDriver, RobotMap.buttonY);
	static final JoystickButton buttonA_dr = new JoystickButton(xboxDriver, RobotMap.buttonA);

	static final JoystickButton buttonStart_dr = new JoystickButton(xboxDriver, RobotMap.buttonStart);
	static final JoystickButton buttonSelect_dr = new JoystickButton(xboxDriver, RobotMap.buttonSelect);
	
	static final JoystickButton buttonTriggerRT_dr = new JoystickButton(xboxDriver, RobotMap.rightTrigger);
	static final JoystickButton buttonTriggerLT_dr = new JoystickButton(xboxDriver, RobotMap.leftTrigger);
	
	//*****************************************************************************************
	static final Joystick xboxAssist = new Joystick(RobotMap.xboxControllerAssist);
	
	static final JoystickButton buttonY_as = new JoystickButton(xboxAssist, RobotMap.buttonY);
	static final JoystickButton buttonA_as = new JoystickButton(xboxAssist, RobotMap.buttonA);
	
	static final JoystickButton buttonStart_as = new JoystickButton(xboxAssist, RobotMap.buttonStart);
	static final JoystickButton buttonSelect_as = new JoystickButton(xboxAssist, RobotMap.buttonSelect);
	
	static final JoystickButton buttonB_as = new JoystickButton(xboxAssist, RobotMap.buttonB);
	static final JoystickButton buttonX_as = new JoystickButton(xboxAssist, RobotMap.buttonX);
	
	static final JoystickButton buttonBumperLeft_as = new JoystickButton(xboxAssist, RobotMap.buttonBumperLeft);
	static final JoystickButton buttonBumperRight_as = new JoystickButton(xboxAssist, RobotMap.buttonBumperRight);

    public void init() {
    	
    	Command clawExtendDown = new ClawExtendDown();
    	Command clawExtendUp = new ClawExtendUp();
    	Command elevatorUp = new ElevatorUpSimple();
    	Command elevatorDown = new ElevatorDownSimple();
    	Command liftDown = new LiftDown();
    	Command liftUp = new LiftUp();
    	Command clawIntakeIn = new ClawIntakeOut();
    	Command clawIntakeOut = new ClawIntakeIn();
    	
    	//start driver xbox controller buttons *********************************
    	buttonY_dr.toggleWhenPressed(clawIntakeIn);
    	buttonA_dr.toggleWhenPressed(clawIntakeOut);

    	buttonX_dr.whenPressed(clawExtendDown);
    	buttonB_dr.whenPressed(clawExtendUp);
    	
    	//start assitant xbox controller buttons *********************************    	
    	buttonY_as.whileHeld(elevatorUp);
    	buttonA_as.whileHeld(elevatorDown);
    	
    	buttonX_as.whileHeld(liftDown);
    	buttonB_as.whileHeld(liftUp);
    }

    public static Joystick getDriverController() {
        return xboxDriver;
    }
    
    public static Joystick getOperatorController() {
        return xboxAssist;
    }
}
