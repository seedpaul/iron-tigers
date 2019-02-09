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
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());


  //************************* Main Driver *********************************//
  public static Joystick XboxDriver = new Joystick(RobotMap.xboxControllerDriver);

  public static JoystickButton buttonA_dr = new JoystickButton(XboxDriver,RobotMap.buttonA);
  public static JoystickButton buttonB_dr = new JoystickButton(XboxDriver,RobotMap.buttonB);
  public static JoystickButton buttonX_dr = new JoystickButton(XboxDriver,RobotMap.buttonX);
  public static JoystickButton buttonY_dr = new JoystickButton(XboxDriver,RobotMap.buttonY);

  // public static JoystickButton buttonBumperRight_dr = new JoystickButton(XboxDriver,RobotMap.buttonBumperRight);
  // public static JoystickButton buttonBumperLeftt_dr = new JoystickButton(XboxDriver,RobotMap.buttonBumperLeft);

  public static JoystickButton startButton_dr = new JoystickButton(XboxDriver,RobotMap.buttonStart);


//************************ Assistant Driver ********************************//
// public static Joystick XboxAssist = new Joystick(RobotMap.xboxControllerAssist);

//   public static JoystickButton buttonBumperRight_as = new JoystickButton(XboxAssist,RobotMap.buttonBumperRight);
//   public static JoystickButton buttonBumperLeft_as = new JoystickButton(XboxAssist,RobotMap.buttonBumperLeft);

//   public static JoystickButton buttonA_as = new JoystickButton(XboxAssist,RobotMap.buttonA);
//   public static JoystickButton buttonY_as = new JoystickButton(XboxAssist,RobotMap.buttonY);

//   public static JoystickButton buttonX_as = new JoystickButton(XboxAssist,RobotMap.buttonX);
//   public static JoystickButton buttonB_as = new JoystickButton(XboxAssist,RobotMap.buttonB);


//************************************* Robot with claw mechanism *********************************************//
  public void init(){

    //********** Main Driver *****************************************************//
    //TODO: enter commands into butons

    // buttonA_dr.whenPressed(command); //close claw total
    // buttonB_dr.whenPressed(command); //grab ball
    // buttonY_dr.whenPressed(command); //open claw total
    // buttonX_dr.whenPressed(command); //grab panel
    // buttonBumperRight_dr.whenPressed(command); //deliver ball
    
    buttonA_dr.whenPressed(new EnablePID()); //close claw total
    buttonY_dr.whenPressed(new disablePID()); //open claw total
    startButton_dr.whenPressed(new ToggleCameras());

    buttonB_dr.whenPressed(new TurnToAngle()); //grab ball
    buttonX_dr.whenPressed(new TurnToAngle2()); //grab panel

    // buttonY_dr.whenPressed(command); //open claw total
    // buttonX_dr.whenPressed(command); //grab panel

    //********* Assist Driver *******************************************************//
    // buttonY_as.whenPressed(new ElevatorUp());
    // buttonA_as.whenPressed(new ElevatorDown());

    //TODO: Make an up to leven 6 and up to level 9
    // buttonBumperRight_as.whenPressed(new LiftUp()); //level 9
    // buttonBumperLeft_as.whenPressed(new LiftUp()); //level 6

  }

//************************************ Robot with Wheel intake **************************************//
  //TODO: make sure the controlls for Mark's panel pick-up is figured out (the flipper )

  // public void init(){
    
  //   //************ Main Driver *********//
  //TODO: enter commands into buttons
  //   buttonA_dr.whenPressed(command); //wheels in (pick up ball)
  //   buttonY_dr.whenPressed(command); //wheels out (shoot ball)

  //   buttonB_dr.whenPressed(command); //elbow down
  //   buttonX_dr.whenPressed(command); //elbow up

  //   startButton_dr.whenPressed(new ToggleCameras());

  //   //********** Assistant Driver **********//
  //   buttonY_as.whenPressed(new ElevatorUp());
  //   buttonA_as.whenPressed(new ElevatorDown());

  //   buttonB_as.whenPressed(new openFlipper());
  //   buttonX_as.whenPressed(new closeFlipper());

  //   //TODO: Make an up to leven 6 and up to level 9
  //   buttonBumperRight_as.whenPressed(new LiftUp()); //level 9
  //   buttonBumperLeft_as.whenPressed(new LiftUp()); //level 6
  // }
  
}
