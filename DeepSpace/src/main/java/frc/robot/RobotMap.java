package frc.robot;

public class RobotMap {

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

  public static int TalonIntakeWheels = 3;
  public static int TalonIntakeFlipper = 8;

  public static int TalonIntakeElbow = 1;
//*******************end can*****************************/

//****************** DIO ports for encoders**************/
  public static int leftEncoderChannelA = 4;
  public static int leftEncoderChannelB = 5;
  public static int rightEncoderChannelA = 2;
  public static int rightEncoderChannelB = 3;

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
