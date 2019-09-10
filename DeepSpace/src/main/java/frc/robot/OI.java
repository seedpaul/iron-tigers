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

public class OI {

  //************************* Main Driver *********************************//
  public static Joystick XboxDriver = new Joystick(RobotMap.genericController);

  public static JoystickButton buttonA = new JoystickButton(XboxDriver,RobotMap.genericButtonA);
  public static JoystickButton buttonB = new JoystickButton(XboxDriver,RobotMap.genericButtonB);
  public static JoystickButton buttonX = new JoystickButton(XboxDriver,RobotMap.genericButtonX);
  public static JoystickButton buttonY = new JoystickButton(XboxDriver,RobotMap.genericButtonY);

  public static JoystickButton buttonBumperRight = new JoystickButton(XboxDriver,RobotMap.genericButtonBumperRight);
  public static JoystickButton buttonBumperLeft = new JoystickButton(XboxDriver,RobotMap.genericButtonBumperLeft);

  public static JoystickButton startButton = new JoystickButton(XboxDriver,RobotMap.genericButtonStart);
  public static JoystickButton backButton = new JoystickButton(XboxDriver,RobotMap.genericButtonBack);

  public static JoystickButton buttonTriggerRight = new JoystickButton(XboxDriver,RobotMap.genericRightTrigger);
  public static JoystickButton buttonTriggerLeft = new JoystickButton(XboxDriver,RobotMap.genericLeftTrigger);

  public void init(){

    buttonBumperLeft.whenPressed(new IntakeWheelsInjest());
    
    buttonBumperRight.whenPressed(new IntakeWheelsEject());
    buttonBumperRight.whenReleased(new IntakeWheelsStop());

    buttonTriggerRight.whenPressed(new ElbowUp());
    buttonTriggerLeft.whenPressed(new ElbowDown()); 

    buttonY.whenPressed(new ElevatorUp()); 
    buttonY.whenReleased(new ElevatorStop());

    buttonA.whenPressed(new ElevatorDown()); 
    buttonA.whenReleased(new ElevatorStop());

    buttonX.whenPressed(new FlipperClose());
    buttonB.whenPressed(new FlipperOpen());

  }
  
}
