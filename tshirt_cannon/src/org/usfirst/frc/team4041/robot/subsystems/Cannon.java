

package org.usfirst.frc.team4041.robot.subsystems;

import org.usfirst.frc.team4041.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Cannon extends Subsystem {

	private static Cannon instance;
	public Compressor compressor = new Compressor();
	private Timer t = new Timer();

	private static final Solenoid solenoidCannon = new Solenoid(RobotMap.firingSolenoid);
	private static final Relay spikeLight = new Relay(RobotMap.spike_relay_light,Relay.Direction.kForward);
	private static final Relay spikeFans = new Relay(RobotMap.spike_relay_fans,Relay.Direction.kForward);

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
		t.start();
	}
	
	public void close() { 
		solenoidCannon.set(false);
	}

	public void open() { 

		System.out.println("fire cannon");
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
	public void toggleCompressors() {
		
		if(compressor.getClosedLoopControl()) {
			compressor.setClosedLoopControl(false);
		}else {
			compressor.setClosedLoopControl(true);
		}
	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}
	
	public void lightOn(){
		spikeLight.set(Relay.Value.kOn);
	}
	public void lightOff(){
		spikeLight.set(Relay.Value.kOff);
	}
	
	public void fansOn(){
		spikeFans.set(Relay.Value.kOn);
	}
	public void fansOff(){
		
		if(t.get() > 55.0) {
			spikeFans.set(Relay.Value.kOff);
			t.reset();
		}
	}

}

