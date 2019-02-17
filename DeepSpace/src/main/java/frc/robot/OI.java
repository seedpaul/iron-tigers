/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;

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


//************************ Assistant Driver ********************************//
//  public static Joystick XboxAssist = new Joystick(RobotMap.xboxControllerAssist);


  // public static JoystickButton buttonA_as = new JoystickButton(XboxAssist,RobotMap.buttonA);
  // public static JoystickButton buttonB_as = new JoystickButton(XboxAssist,RobotMap.buttonB);
  // public static JoystickButton buttonY_as = new JoystickButton(XboxAssist,RobotMap.buttonY);
  // public static JoystickButton buttonX_as = new JoystickButton(XboxAssist,RobotMap.buttonX);

  //   public static JoystickButton buttonBumperRight_as = new JoystickButton(XboxAssist,RobotMap.buttonBumperRight);
  //   public static JoystickButton buttonBumperLeft_as = new JoystickButton(XboxAssist,RobotMap.buttonBumperLeft);

//************************************* Robot with claw mechanism *********************************************//
  public void init(){

    //********** Main Driver *****************************************************//
    //TODO: enter commands into buttons

    // buttonA_dr.whenPressed(command); //close claw total 
    // buttonB_dr.whenPressed(command); //grab ball
    // buttonY_dr.whenPressed(command); //open claw total
    // buttonX_dr.whenPressed(command); //grab panel
    // buttonBumperRight_dr.whenPressed(command); //deliver ball
    
    // buttonA_dr.whenPressed(new EnablePID()); //close claw total
    // buttonY_dr.whenPressed(new disablePID()); //open claw total
    startButton_dr.whenPressed(new ToggleCameras());
    // startButton_dr.whenPressed(new FlipperClose());

    // buttonB_dr.whenPressed(new FrontLiftClimb()); //grab ball
    // buttonX_dr.whenPressed(new FrontLiftHome()); //grab panel

    // buttonY_dr.whenPressed(new FrontLiftUp()); //open claw total
    // buttonA_dr.whenPressed(new FrontLiftDown()); //grab panel

    //********* Assist Driver *******************************************************//
    buttonY_dr.whenPressed(new FlipperOpen());
    // buttonX_dr.whenPressed(new RearLiftHab6());
    buttonA_dr.whenPressed(new FlipperClose());

    //TODO: Make an up to level 6 and up to level 9
    // buttonBumperRight_dr.whenPressed(new ElevatorDown()); //level 9
    // buttonBumperLeft_dr.whenPressed(new ElevatorUp()); //level 6

  }
  
}
