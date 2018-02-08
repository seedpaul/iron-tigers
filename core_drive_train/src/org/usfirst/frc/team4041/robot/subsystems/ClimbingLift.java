package org.usfirst.frc.team4041.robot.subsystems;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimbingLift extends Subsystem {

	static final Talon liftTalon = new Talon(RobotMap.liftTalonPWM);
	private static ClimbingLift instance;

	public ClimbingLift() {
		initialize();
	}

	public static ClimbingLift getInstance() {
		if (instance == null) {
			instance = new ClimbingLift();
		}
		return instance;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    private void initialize() {
		liftTalon.setSafetyEnabled(false);
	}

	public void up() {
		liftTalon.set(0.3);
	}

	public void down() {
		liftTalon.set(-0.3);
	}

	public void stop() {
		liftTalon.stopMotor();
	}
}

