package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.elbow.*;
import frc.robot.commands.intake_wheels.*;
import frc.robot.commands.rear_lift.RearLiftHab19;
import frc.robot.commands.rear_lift.RearLiftHome;
import frc.robot.commands.elevator.*;
import frc.robot.commands.flipper.*;
import frc.robot.commands.front_lift.FrontLiftClimbStep2;
import frc.robot.commands.front_lift.FrontLiftHome;
import frc.robot.commands.camera.*;
import frc.robot.command_groups.*;

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

  public static JoystickButton startButton_as = new JoystickButton(xboxControllerAssist,RobotMap.buttonStart);
  public static JoystickButton selectButton_as = new JoystickButton(xboxControllerAssist,RobotMap.buttonSelect);

  public void init(){

    //************************* Main ***************************//
    buttonX_dr.whenPressed(new IntakeWheelsInjest());
    
    buttonB_dr.whileHeld(new IntakeWheelsEject());
    buttonB_dr.whenReleased(new IntakeWheelsStop()); 

    buttonY_dr.whenPressed(new ElbowUp());
    buttonA_dr.whenPressed(new ElbowDown()); 

    buttonBumperRight_dr.whenPressed(new FrontLiftHome());
    buttonBumperLeft_dr.whenPressed(new RearLiftHome()); 
    

    //**************************** Assistant ************************//
    buttonY_as.whenPressed(new ElevatorUp()); 
    buttonA_as.whenPressed(new ElevatorDown()); 

    buttonX_as.whenPressed(new FlipperClose());
    buttonB_as.whenPressed(new FlipperOpen());
	
	  buttonBumperRight_as.whenPressed(new FrontLiftClimbStep2()); 
    buttonBumperLeft_as.whenPressed(new RearLiftHab19()); 
	
	  startButton_as.whenPressed(new ToggleCameras()); 
  }
}
