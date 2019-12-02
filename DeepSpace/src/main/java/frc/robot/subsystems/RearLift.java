package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.RobotMap;
import frc.robot.subsystems.positioning.*;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RearLift extends Subsystem {

  private static TalonSRX rearLiftTalon = new TalonSRX(RobotMap.TalonRearLift);
  private static RearLift instance;

  private RearLift(){
    init();
  }

  public static RearLift getInstance(){
    if (instance == null){
      instance = new RearLift();
    }
    return instance;
  }

  private void init(){

    rearLiftTalon.configFactoryDefault();

    rearLiftTalon.set(ControlMode.PercentOutput,0);
    rearLiftTalon.setNeutralMode(NeutralMode.Brake);

    //this is a very important line of code in order to make foward on motor line up with foward on encoder
    rearLiftTalon.setSensorPhase(false);

    rearLiftTalon.configForwardSoftLimitEnable(true);
    rearLiftTalon.configReverseSoftLimitEnable(true);

    rearLiftTalon.configForwardSoftLimitThreshold(RearLiftPositions.getHomePosition());
    rearLiftTalon.configReverseSoftLimitThreshold(RearLiftPositions.getHighestPosition());

    rearLiftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    
    rearLiftTalon.configNominalOutputForward(0,30);
    rearLiftTalon.configNominalOutputReverse(0,30);
    rearLiftTalon.configPeakOutputForward(1, 30);
    rearLiftTalon.configPeakOutputReverse(-1, 30);

    rearLiftTalon.configAllowableClosedloopError(0, 0, 30);

		rearLiftTalon.config_kF(0, 0.0, 30);
		rearLiftTalon.config_kP(0, 1.0, 30);
		rearLiftTalon.config_kI(0, 0.0, 30);
    rearLiftTalon.config_kD(0, 1.0, 30);

    rearLiftTalon.configPeakCurrentLimit(65, 30);
    rearLiftTalon.configPeakCurrentDuration(120, 30);
    rearLiftTalon.configContinuousCurrentLimit(25, 30);
    rearLiftTalon.enableCurrentLimit(true);

    //pre-flight checklist to make sure rear lift is all the way up
    rearLiftTalon.setSelectedSensorPosition(RearLiftPositions.rear_home,0,30);
  }

  public void goToLevel6(){
    setPosition(RearLiftPositions.rear_habLevel6);
  }

  public void goToLevel19(){
    setPosition(RearLiftPositions.rear_habLevel19);
  }

  public void goToHome(){
    setPosition(RearLiftPositions.rear_home);
  }

  private void setPosition(int position){
    rearLiftTalon.set(ControlMode.Position, position);
    rearLiftTalon.getSelectedSensorPosition();
  }

  public int getSensorValue(){
    return rearLiftTalon.getSelectedSensorPosition();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
