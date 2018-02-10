package org.usfirst.frc.team4041.robot.subsystems;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimbingLift extends Subsystem {

	static final VictorSP liftVictor = new VictorSP(RobotMap.liftVictorSPPWM);
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
		liftVictor.setSafetyEnabled(false);
	}

	public void up() {
		liftVictor.set(0.3);
	}

	public void down() {
		liftVictor.set(-0.3);
	}

	public void stop() {
		liftVictor.stopMotor();
	}
}

