package org.usfirst.frc.team4041.robot;

public class RobotMap {

	public static final int leftFrontDriveTalonSRX = 1;
	public static final int leftRearDriveTalonSRX = 2;
	public static final int rightFrontDriveTalonSRX = 3;
	public static final int rightRearDriveTalonSRX = 4;
	
	public static final int leftEncoderPt1 = 0;
	public static final int leftEncoderPt2 = 1;
	public static final int rightEncoderPt1 = 2;
	public static final int rightEncoderPt2 = 3;
	
	public static final int elevatorEncoderPt1 = 4;
	public static final int elevatorEncoderPt2 = 5;
	
	public static final int elevatorVictorSPPWM = 0;
	public static final int clawExtendSparkPWM = 1;
	public static final int clawIntakeRightSparkPWM = 2;
	public static final int clawIntakeLeftSparkPWM = 3;
	public static final int liftVictorSPPWM = 4;
	
	public static final int potDIO = 3;

	//start xbox *****************************************
	public static final int xboxControllerDriver = 0;
	//public static final int xboxControllerAssistant = 1;
	
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

	public static final int rightJoystickPush = 12;//new controller may be different
	public static final int leftJoystickPush = 11;//new controller may be different
	//end xbox *****************************************
	
	public static final double DEADZONE_MAGIC_NUMBER = .15;
	public static final double STOP = 0.0;
}
