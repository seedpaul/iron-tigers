package org.usfirst.frc.team4041.robot;

//import org.usfirst.frc.team4041.robot.commandGroups.AutonomousCenterLeftSwitch;
//import org.usfirst.frc.team4041.robot.commandGroups.AutonomousCenterRightSwitch;
import org.usfirst.frc.team4041.robot.commands.ClawExtendDownWithController;
import org.usfirst.frc.team4041.robot.commands.ClawExtendUpWithController;
//import org.usfirst.frc.team4041.robot.commands.ClawIntakeInWithController;
//import org.usfirst.frc.team4041.robot.commands.ClawExtendDownWithController;
//import org.usfirst.frc.team4041.robot.commands.ClawExtendUpWithController;
//import org.usfirst.frc.team4041.robot.commands.ClawIntakeInWithController;
//import org.usfirst.frc.team4041.robot.commands.ClawIntakeOutWithController;
//import org.usfirst.frc.team4041.robot.commands.ElevatorDownWithController;
//import org.usfirst.frc.team4041.robot.commands.ElevatorUpWithController;
//import org.usfirst.frc.team4041.robot.commands.LiftDownWithController;
//import org.usfirst.frc.team4041.robot.commands.LiftUpWithController;
//import org.usfirst.frc.team4041.robot.commands.ClawIntakeOutWithController;
import org.usfirst.frc.team4041.robot.commands.ElevatorDownWithControllerSimple;
import org.usfirst.frc.team4041.robot.commands.ElevatorUpWithControllerSimple;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class OI {

	static final Joystick xboxDriver = new Joystick(RobotMap.xboxControllerDriver);
	static final JoystickButton buttonB = new JoystickButton(xboxDriver, RobotMap.buttonB);
	static final JoystickButton buttonX = new JoystickButton(xboxDriver, RobotMap.buttonX);
	
	static final JoystickButton buttonY = new JoystickButton(xboxDriver, RobotMap.buttonY);
	static final JoystickButton buttonA = new JoystickButton(xboxDriver, RobotMap.buttonA);
//	
//	static final JoystickButton buttonBumperLeft = new JoystickButton(xboxDriver, RobotMap.buttonBumperLeft);
//	static final JoystickButton buttonBumperRight = new JoystickButton(xboxDriver, RobotMap.buttonBumperRight);
//
//	static final JoystickButton buttonStart = new JoystickButton(xboxDriver, RobotMap.buttonStart);
//	static final JoystickButton buttonSelect = new JoystickButton(xboxDriver, RobotMap.buttonSelect);
	
    public void init() {
    	
    	//start driver xbox controller buttons *********************************
//    	buttonX.toggleWhenPressed(new AutonomousCenterLeftSwitch());
//    	buttonB.toggleWhenPressed(new AutonomousCenterRightSwitch());
    	
//    	buttonStart.toggleWhenPressed(new ElevatorUpWithController());
//    	buttonSelect.toggleWhenPressed(new ElevatorDownWithController());
//    	
//    	buttonBumperRight.toggleWhenPressed(new ClawIntakeOutWithController());
//    	buttonBumperLeft.toggleWhenPressed(new ClawIntakeInWithController());
//    	
    	Command clawExtendDown = new ClawExtendDownWithController();
    	Command clawExtendUp = new ClawExtendUpWithController();
    	
    	buttonA.whenPressed(clawExtendDown);
    	buttonY.whenPressed(clawExtendUp);
    	
    	Command elevatorUp = new ElevatorUpWithControllerSimple();
    	Command elevatorDown = new ElevatorDownWithControllerSimple();
    	
    	buttonX.whenPressed(elevatorUp);
    	buttonB.whenPressed(elevatorDown);
    }

    public static Joystick getDriveController() {
        return xboxDriver;
    }
}
