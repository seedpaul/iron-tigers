package frc.team4041.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4041.Robot;
import frc.team4041.RobotMap;

public class ClimbingLift extends Subsystem {

	static final VictorSP liftVictor = new VictorSP(RobotMap.liftVictorSPPWM);
	private static ClimbingLift instance;
	
	private static double upSpeed = 0.7;
	private static double downSpeed = -0.95;

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
		
		boolean allowLift = Robot.matchTimer.get() > 100;
		System.out.println("Allow Lift: " + allowLift);
		if (true) {
			liftVictor.set(upSpeed);
		}
	}

	public void down() {
		liftVictor.set(downSpeed);
	}

	public void stop() {
		liftVictor.stopMotor();
	}
}

