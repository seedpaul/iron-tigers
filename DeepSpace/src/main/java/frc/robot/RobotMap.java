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
    //*****Start Draintrain********************
    public static int VictorFrontRight = 11;
    public static int VictorFrontLeft = 12;
    
    public static int TalonBackRight = 2;
    public static int TalonBackLeft = 4;
    //*****End  Draintrain********************

  public static int TalonFrontLift = 7;
  public static int VictorFrontLift = 10;

  public static int TalonRearLift = 6;
  
  public static int TalonElevator = 5;
  public static int VictorElevator = 9;

   // mapping locations for intake wheels
  public static int TalonIntakeWheels = 3;
  public static int TalonIntakeFlipper = 8;

  public static int TalonIntakeElbow = 1;
//*******************end can*****************************/

//****************** DIO ports for encoders**************/
  public static int leftEncoderChannelA = 4;
  public static int leftEncoderChannelB = 5;
  public static int rightEncoderChannelA = 2;
  public static int rightEncoderChannelB = 3;

  public static int flipperEncoderChannelA = 8;
  public static int flipperEncoderChannelB = 9;

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



  //start momo *****************************************
	
	public static final int buttonTopRight = 4;
	public static final int buttonMiddleRight = 6;
  public static final int buttonBottomRight = 8;
  
	public static final int buttonTopLeft = 3;
	public static final int buttonMiddleLeft = 5;
  public static final int buttonBottomLeft = 7;

  public static final int paddleLeft = 1;
  public static final int paddleRight = 2;
  
	public static final int shifterForward = 10;
	public static final int shifterBackwards = 9;
	
	public static final int pedals = 1;
	public static final int wheel = 0;

  //end momo *****************************************


}
