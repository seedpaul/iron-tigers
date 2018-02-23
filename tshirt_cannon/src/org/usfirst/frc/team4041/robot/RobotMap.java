package org.usfirst.frc.team4041.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static final int xboxController = 0;
	public static final int leftDriveJaguar = 0;
	public static final int rightDriveJaguar = 1;
	public static final int cannonTalon = 2;
	
	public static final double Stop = 0.0;
	public static final double DEADZONE_MAGIC_NUMBER = .15;

	public static final int button1_generic = 1;
	public static final int button2_generic = 2;
	public static final int button3_generic = 3;
	public static final int button4_generic= 4;
	
	public static final int button9_generic = 9;
	public static final int button10_generic = 10;
	
	public static final int buttonBumperLeft = 5;
	public static final int buttonBumperRight = 6;
	
	public static final int rightTrigger_generic = 8;
	public static final int leftTrigger_generic = 7;

	public static final int rightStickY_generic = 3;
	public static final int rightStickX_generic = 4;
	
	public static final int leftStickY_generic= 1;
	public static final int leftStickX_generic = 0;
	
	public static final int rightJoystickPush_generic = 12;
	public static final int leftJoystickPush_generic = 11;

	public static final int spike_relay_light = 2;

}
