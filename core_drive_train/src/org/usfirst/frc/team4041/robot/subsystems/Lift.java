package org.usfirst.frc.team4041.robot.subsystems;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {

	static final Talon liftTalon = new Talon(RobotMap.liftTalonPWM);
	private static Lift instance;

	public Lift() {
		initialize();
	}

	public static Lift getInstance() {
		if (instance == null) {
			instance = new Lift();
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

