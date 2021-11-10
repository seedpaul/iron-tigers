

package frc.robot;

public class RobotMap {

    //This is where we store all locations of buttons and subsystems

    //DriveTrain
    public static final int FrontRightTalon = 20; //PDP:13
	public static final int FrontLeftTalon = 23; //PDP:1
	public static final int BackRightTalon = 22; //PDP:14
	public static final int BackLeftTalon = 21; //PDP:13
	public static final int TopRightTalon = 24; //PDP:3
	public static final int TopLeftTalon = 25; //PDP:2
    //end DriveTrain

	//Turret
	public static final int TurretTalon = 6; //PDP:8
	public static final int TurretChannelA = 0;
	public static final int TurretChannelB = 1;
	//end Turret

	//Shooter
	public static final int ShooterSparkMax1 = 31; //PDP:15
	public static final int ShooterSparkMax2 = 32; //PDP:0
	//end Shooter

	//Intake Elbow
	public static final int IntakeElbowSparkMax = 30; //PDP:9
	//end Intake Elbow

	//Intake Wheels
	public static final int IntakeWheelsTalon = 7; //PDP:11
	//end Intake Wheels

	//Ranger Sensor
	public static final int TimeOfFlightInternal = 1;
	public static final int TimeOfFlightExternal = 2;
	//end Ranger Sensor

	//Indexer
	public static final int IndexerTalon = 2; //PDP:7
	//end Indexer

	//Elevator Arm
	public static final int ElevatorArmTalon = 95; //PDP:??
	//end Elevator Arm

	//Elevator
	public static final int ElevatorLeftTalon = 5; //PDP:??
	public static final int ElevatorRightTalon = 3; //PDP:??
	//end Elevator

	//Bar Leveler
	public static final int BarLeverTalon = 9; //PDP:??
	//end Bar Leveler

	//PDP
	public static final int PDPID = 0;
	public static final int pdpIndexer = 7;
	public static final int pdpIntake = 11;
	
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
	public static final int leftTrigger = 2;

	public static final int rightJoystickPush = 12;
	public static final int leftJoystickPush = 11;

  //end xbox *****************************************

}