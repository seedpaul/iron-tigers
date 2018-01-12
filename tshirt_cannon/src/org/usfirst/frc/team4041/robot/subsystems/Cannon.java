

package org.usfirst.frc.team4041.robot.subsystems;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Cannon extends Subsystem {

	private static Cannon instance;

	private static final Solenoid solenoidCannon = new Solenoid(0);
	private static final Relay spikeLight = new Relay(RobotMap.spike_relay_light,Relay.Direction.kForward);
	//private static final Solenoid solenoidValue = new Solenoid(1);


	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Cannon() {
		// do some stuff here it need be!
		initialize();
	}

	public static Cannon getInstance() {

		if (instance == null) {
			instance = new Cannon();
		}
		return instance;
	}

	public void initialize() {

	}
	
	public void close() { 
		solenoidCannon.set(false);
	}

	public void open() { 

		solenoidCannon.set(true);// open cannon
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		solenoidCannon.set(false);//close cannon
		solenoidCannon.clearAllPCMStickyFaults();

	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}
	public static void lightOn(){
		spikeLight.set(Relay.Value.kOn);
	}
	public static void lightOff(){
		spikeLight.set(Relay.Value.kOff);
	}

}

