package org.usfirst.frc.team4041.robot;

public class RobotMap {

	public static final int leftFrontDriveTalonSRX = 3;
	public static final int leftRearDriveTalonSRX = 4;
	public static final int rightFrontDriveTalonSRX = 1;
	public static final int rightRearDriveTalonSRX = 2;
	
	public static final int leftEncoderPt1 = 4;
	public static final int leftEncoderPt2 = 5;
	public static final int rightEncoderPt1 = 2;
	public static final int rightEncoderPt2 = 3;
	
	public static final int elevatorEncoderPt1 = 6;
	public static final int elevatorEncoderPt2 = 7;
	
	public static final int elevatorTalonPWM = 9;
	public static final int clawExtendSparkPWM = 1;
	public static final int clawIntakeRightSparkPWM = 2;
	public static final int clawIntakeLeftSparkPWM = 3;
	public static final int liftVictorSPPWM = 4;
	
	public static final int potDIO = 3;

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
	
	//start generic*****************************************
	public static final int genericControllerAssist = 1;
	
	public static final int genericButton1 = 1;
	public static final int genericButton2 = 2;
	public static final int genericButton3 = 3;
	public static final int genericButton5 = 4;
	
	public static final int genericButtonBumperLeft = 5;
	public static final int genericButtonBumperRight = 6;
	
	public static final int genericLeftTrigger = 7;
	public static final int genericRightTrigger = 8;
	
	public static final int genericButton9 = 9;
	public static final int genericButton10 = 10;
	
	public static final int genericLeftJoystickPush = 11;
	public static final int genericRightJoystickPush = 12;
	
	public static final int genericLeftStickY = 1;
	public static final int genericRightStickX = 4;
	
	public static final int genericRightStickY = 3;
	public static final int genericLeftStickX = 0;
	
	//end generic *****************************************
	
	public static final double DEADZONE_MAGIC_NUMBER = .15;
	public static final double STOP = 0.0;
}
