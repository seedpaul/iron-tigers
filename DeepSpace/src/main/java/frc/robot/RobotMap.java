/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;


  //*********************start can********************/
  
  //*****Draintrain********************
  public static int SPXFrontRight = 11;
  public static int SPXFrontLeft = 12;
  
  public static int SRXBackRight = 2;
  public static int SRXBackLeft = 4;
  //*****Draintrain********************

  public static int SRXFrontLift = 7;
  public static int SPXFrontLift = 10;

  public static int SRXRearLift = 6;
  
  public static int SRXElevator = 5;
  public static int SPXElevator = 9;

  public static int TalonIntakeClaw = 0;
  public static int VictorIntakeWheels = 0;
  public static int VictorIntakeFlipper = 0;

  public static int SRXIntakeElbow = 1;
//*******************end can*****************************/

  public static int leftEncoderChannelA = 4;
  public static int leftEncoderChannelB = 5;
  public static int rightEncoderChannelA = 2;
  public static int rightEncoderChannelB = 3;

  public static int flipperEncoderChannelA = 6;
  public static int flipperEncoderChannelB = 7;

  	//start xbox *****************************************
	public static final int xboxControllerDriver = 0;
	public static final int xboxControllerAssist = 1;
	
	public static final int buttonA = 1;
	public static final int buttonB = 2;
	public static final int buttonX = 3;
	public static final int buttonY = 4;
	
	public static final int buttonBumperLeft = 5;
	public static final int buttonBumperRight = 6;
	
	public static final int buttonSelect = 7;
	public static final int buttonStart = 8;
	
	public static final int rightStickY = 5;
	public static final int leftStickY = 1;
	
	public static final int rightStickX = 4;
	public static final int leftStickX = 0;
	
	public static final int rightTrigger = 3;
	public static final int leftTrigger = 3;

	public static final int rightJoystickPush = 12;
	public static final int leftJoystickPush = 11;
	//end xbox *****************************************


}
