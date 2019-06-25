package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.hal.PDPJNI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeWheels extends Subsystem {

  private static final VictorSP intakeWheelsVictor = new VictorSP(6);
  private static IntakeWheels instance;

  private IntakeWheels(){
    init();
  }

  public void periodic(){
    SmartDashboard.putNumber("intake wheels",getCurrent(15)); 
  }

  public static IntakeWheels getInstance(){
    if (instance==null){
      instance = new IntakeWheels();
    }
    return instance;
  }

  private void init(){

    intakeWheelsVictor.setExpiration(1);
    intakeWheelsVictor.setSafetyEnabled(true);

  }

  public void injest(){
    intakeWheelsVictor.set(0.5);
  }

  public void hold(){
    intakeWheelsVictor.set(0.1);
  }

  public void eject(){
    intakeWheelsVictor.set(-1.0);
  }

  public void stop(){
    intakeWheelsVictor.set(0.0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public double getCurrent(int channel) {

		double current = PDPJNI.getPDPChannelCurrent((byte)channel, 0);
		PDPJNI.checkPDPChannel(channel);

		return current;
	}


}
