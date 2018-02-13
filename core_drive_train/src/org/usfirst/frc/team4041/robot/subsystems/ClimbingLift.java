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
		//Invert speed controller so up is positive.
		//Seems to make more sense that up being negative
		liftVictor.setInverted(true);
	}

	public void up() {
		liftVictor.set(0.6);
	}

	public void down() {
		liftVictor.set(-0.95);
	}

	public void stop() {
		liftVictor.stopMotor();
	}
}

