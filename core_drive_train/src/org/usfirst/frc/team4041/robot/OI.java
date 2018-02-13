package org.usfirst.frc.team4041.robot;

import org.usfirst.frc.team4041.robot.commands.teleop.ClawExtendDownSimple;
import org.usfirst.frc.team4041.robot.commands.teleop.ClawExtendUpSimple;
import org.usfirst.frc.team4041.robot.commands.teleop.ClawIntakeIn;
import org.usfirst.frc.team4041.robot.commands.teleop.ClawIntakeOut;
import org.usfirst.frc.team4041.robot.commands.teleop.ElevatorDownSimple;
import org.usfirst.frc.team4041.robot.commands.teleop.ElevatorUpSimple;
import org.usfirst.frc.team4041.robot.commands.teleop.LiftDown;
import org.usfirst.frc.team4041.robot.commands.teleop.LiftUp;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class OI {

	static final Joystick xboxDriver = new Joystick(RobotMap.xboxControllerDriver);
	
	static final JoystickButton buttonB_dr = new JoystickButton(xboxDriver, RobotMap.buttonB);
	static final JoystickButton buttonX_dr = new JoystickButton(xboxDriver, RobotMap.buttonX);
	
	static final JoystickButton buttonY_dr = new JoystickButton(xboxDriver, RobotMap.buttonY);
	static final JoystickButton buttonA_dr = new JoystickButton(xboxDriver, RobotMap.buttonA);
	
	static final JoystickButton buttonBumperLeft_dr = new JoystickButton(xboxDriver, RobotMap.buttonBumperLeft);
	static final JoystickButton buttonBumperRight_dr = new JoystickButton(xboxDriver, RobotMap.buttonBumperRight);

	static final JoystickButton buttonStart_dr = new JoystickButton(xboxDriver, RobotMap.buttonStart);
	static final JoystickButton buttonSelect_dr = new JoystickButton(xboxDriver, RobotMap.buttonSelect);
	
	//*****************************************************************************************
	static final Joystick xboxOperator = new Joystick(RobotMap.xboxControllerOperator);
	
	static final JoystickButton buttonB_op = new JoystickButton(xboxOperator, RobotMap.buttonB);
	static final JoystickButton buttonX_op = new JoystickButton(xboxOperator, RobotMap.buttonX);
	
	static final JoystickButton buttonY_op = new JoystickButton(xboxOperator, RobotMap.buttonY);
	static final JoystickButton buttonA_op = new JoystickButton(xboxOperator, RobotMap.buttonA);
	
	static final JoystickButton buttonBumperLeft_op = new JoystickButton(xboxOperator, RobotMap.buttonBumperLeft);
	static final JoystickButton buttonBumperRight_op = new JoystickButton(xboxOperator, RobotMap.buttonBumperRight);

	static final JoystickButton buttonStart_op = new JoystickButton(xboxOperator, RobotMap.buttonStart);
	static final JoystickButton buttonSelect_op = new JoystickButton(xboxOperator, RobotMap.buttonSelect);
	
    public void init() {
    	
    	Command clawExtendDown = new ClawExtendDownSimple();
    	Command clawExtendUp = new ClawExtendUpSimple();
    	Command elevatorUpSimple = new ElevatorUpSimple();
    	Command elevatorDownSimple = new ElevatorDownSimple();
    	Command liftDown = new LiftDown();
    	Command liftUp = new LiftUp();
    	Command clawIntakeIn = new ClawIntakeOut();
    	Command clawIntakeOut = new ClawIntakeIn();
    	
    	//start driver xbox controller buttons *********************************
    	buttonStart_dr.toggleWhenPressed(liftDown);
    	buttonSelect_dr.toggleWhenPressed(liftUp);
    	
    	buttonBumperRight_dr.toggleWhenPressed(clawIntakeOut);
    	buttonBumperLeft_dr.toggleWhenPressed(clawIntakeIn);

    	buttonX_dr.whenPressed(clawExtendDown);
    	buttonB_dr.whenPressed(clawExtendUp);
    	    	
    	buttonY_dr.toggleWhenPressed(elevatorUpSimple);
    	buttonA_dr.toggleWhenPressed(elevatorDownSimple);
    }

    public static Joystick getDriverController() {
        return xboxDriver;
    }
    
    public static Joystick getOperatorController() {
        return xboxOperator;
    }
}
