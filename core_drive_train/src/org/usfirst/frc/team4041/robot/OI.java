package org.usfirst.frc.team4041.robot;

import org.usfirst.frc.team4041.robot.commandGroups.AutonomousCenterLeftSwitch;
import org.usfirst.frc.team4041.robot.commandGroups.AutonomousCenterRightSwitch;
//import org.usfirst.frc.team4041.robot.commands.ClawExtendDownWithController;
//import org.usfirst.frc.team4041.robot.commands.ClawExtendUpWithController;
//import org.usfirst.frc.team4041.robot.commands.ClawIntakeInWithController;
//import org.usfirst.frc.team4041.robot.commands.ClawIntakeOutWithController;
//import org.usfirst.frc.team4041.robot.commands.ElevatorDownWithController;
//import org.usfirst.frc.team4041.robot.commands.ElevatorUpWithController;
//import org.usfirst.frc.team4041.robot.commands.LiftDownWithController;
//import org.usfirst.frc.team4041.robot.commands.LiftUpWithController;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	static final Joystick xboxDriver = new Joystick(RobotMap.xboxControllerDriver);
	static final JoystickButton buttonB = new JoystickButton(xboxDriver, RobotMap.buttonB);
	static final JoystickButton buttonX = new JoystickButton(xboxDriver, RobotMap.buttonX);
	
//	static final JoystickButton buttonY = new JoystickButton(xboxDriver, RobotMap.buttonY);
//	static final JoystickButton buttonA = new JoystickButton(xboxDriver, RobotMap.buttonA);
//	
//	static final JoystickButton buttonBumperLeft = new JoystickButton(xboxDriver, RobotMap.buttonBumperLeft);
//	static final JoystickButton buttonBumperRight = new JoystickButton(xboxDriver, RobotMap.buttonBumperRight);
//
//	static final JoystickButton buttonStart = new JoystickButton(xboxDriver, RobotMap.buttonStart);
//	static final JoystickButton buttonSelect = new JoystickButton(xboxDriver, RobotMap.buttonSelect);
	
    public void init() {
    	
    	//start driver xbox controller buttons *********************************
    	buttonX.toggleWhenPressed(new AutonomousCenterLeftSwitch());
    	buttonB.toggleWhenPressed(new AutonomousCenterRightSwitch());
    	
//    	buttonY.toggleWhenPressed(new ElevatorUpWithController());
//    	buttonA.toggleWhenPressed(new ElevatorDownWithController());
//    	
//    	buttonBumperRight.toggleWhenPressed(new ClawIntakeOutWithController());
//    	buttonBumperLeft.toggleWhenPressed(new ClawIntakeInWithController());
//    	
//    	buttonStart.toggleWhenPressed(new LiftDownWithController());
//    	buttonSelect.toggleWhenPressed(new LiftUpWithController());
    }

    public static Joystick getDriveController() {
        return xboxDriver;
    }
}
