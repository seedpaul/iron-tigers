/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.elbow.*;
import frc.robot.commands.intake_wheels.*;
import frc.robot.commands.elevator.*;
import frc.robot.commands.flipper.*;
import frc.robot.command_groups.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  //************************* Main Driver *********************************//
  public static Joystick XboxDriver = new Joystick(RobotMap.xboxControllerDriver);

  public static JoystickButton buttonA_dr = new JoystickButton(XboxDriver,RobotMap.buttonA);
  public static JoystickButton buttonB_dr = new JoystickButton(XboxDriver,RobotMap.buttonB);
  public static JoystickButton buttonX_dr = new JoystickButton(XboxDriver,RobotMap.buttonX);
  public static JoystickButton buttonY_dr = new JoystickButton(XboxDriver,RobotMap.buttonY);

  public static JoystickButton buttonBumperRight_dr = new JoystickButton(XboxDriver,RobotMap.buttonBumperRight);
  public static JoystickButton buttonBumperLeft_dr = new JoystickButton(XboxDriver,RobotMap.buttonBumperLeft);

  public static JoystickButton startButton_dr = new JoystickButton(XboxDriver,RobotMap.buttonStart);
  public static JoystickButton selectButton_dr = new JoystickButton(XboxDriver,RobotMap.buttonSelect);

  //************************ Assistant Driver ************************************//
  public static Joystick xboxControllerAssist = new Joystick(RobotMap.xboxControllerAssist);

  public static JoystickButton buttonA_as = new JoystickButton(xboxControllerAssist,RobotMap.buttonA);
  public static JoystickButton buttonB_as = new JoystickButton(xboxControllerAssist,RobotMap.buttonB);
  public static JoystickButton buttonX_as = new JoystickButton(xboxControllerAssist,RobotMap.buttonX);
  public static JoystickButton buttonY_as = new JoystickButton(xboxControllerAssist,RobotMap.buttonY);

  public static JoystickButton buttonBumperLeft_as = new JoystickButton(xboxControllerAssist,RobotMap.buttonBumperLeft);
  public static JoystickButton buttonBumperRight_as = new JoystickButton(xboxControllerAssist,RobotMap.buttonBumperRight);

  public void init(){

    //************************* Main ***************************//
    buttonBumperRight_dr.whenPressed(new IntakeWheelsInjest());
    
    buttonBumperLeft_dr.whileHeld(new IntakeWheelsEject());
    buttonBumperLeft_dr.whenReleased(new IntakeWheelsStop()); 

    buttonY_dr.whenPressed(new ElbowUp());
    buttonA_dr.whenPressed(new ElbowDown()); 

    //**************************** Assistnt ************************//
    buttonY_as.whileHeld(new ElevatorUp()); 
    buttonY_as.whenReleased(new ElevatorStop());

    buttonA_as.whileHeld(new ElevatorDown()); 
    buttonA_as.whenReleased(new ElevatorStop());

    buttonX_as.whenPressed(new FlipperClose());
    buttonB_as.whenPressed(new FlipperOpen());
	
	  buttonBumperRight_as.whenPressed(new ClimbLevel19()); 
    buttonBumperLeft_as.whenPressed(new ClimbLevel6()); 
  }
}
