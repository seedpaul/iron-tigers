package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class Flipper extends Subsystem {

  private static final TalonSRX flipperTalon = new TalonSRX(RobotMap.TalonIntakeFlipper);

  private static int flipper_home = 350;
  private static int flipper_close = 1460;
  private static final int flipper_start = 0;
  private static final int flipperBumpIncrement = 0;

  private static Flipper instance;

  private Flipper(){
    init();
  }

  public static Flipper getInstance(){
    if (instance==null){
      instance = new Flipper();
    }
    return instance;
  }
  
  private void init(){

    flipperTalon.configFactoryDefault();
    flipperTalon.set(ControlMode.Position,0);
    flipperTalon.setNeutralMode(NeutralMode.Brake);

    flipperTalon.configForwardSoftLimitEnable(true);
    flipperTalon.configReverseSoftLimitEnable(true);

    flipperTalon.configForwardSoftLimitThreshold(flipper_close);
    flipperTalon.configReverseSoftLimitThreshold(flipper_home);

    flipperTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);

    flipperTalon.config_kF(0, 0.0, 30);
		flipperTalon.config_kP(0, 1.5, 30);
		flipperTalon.config_kI(0, 0.0, 30);
    flipperTalon.config_kD(0, 15.0, 30);
    
    flipperTalon.configAllowableClosedloopError(0,5,30);

    flipperTalon.configNominalOutputForward(0,30);
    flipperTalon.configNominalOutputReverse(0,30);
    flipperTalon.configPeakOutputForward(1, 30);
    flipperTalon.configPeakOutputReverse(-1, 30);

    flipperTalon.configPeakCurrentLimit(15, 30);
    flipperTalon.configPeakCurrentDuration(60, 30);
    flipperTalon.configContinuousCurrentLimit(1, 30);

    flipperTalon.enableCurrentLimit(true);

    //pre-flight checklist to make sure flipper is all the way up
    flipperTalon.setSelectedSensorPosition(flipper_start,0,30);
  }

  public void homeFlipper(){
    flipperTalon.set(ControlMode.Position, flipper_home);
  }

  public void closeFlipper(){
    flipperTalon.set(ControlMode.Position, flipper_close);
  }

  public void stopFlipper(){
    flipperTalon.set(ControlMode.PercentOutput, 0.0);
  }

  public void bumpFlipperUp(){
    flipper_home += flipperBumpIncrement;
    flipper_close += flipperBumpIncrement; 
    flipperTalon.overrideSoftLimitsEnable(true);
  }

  public void bumpFlipperDown(){
    flipper_home -= flipperBumpIncrement;
    flipper_close -= flipperBumpIncrement;
    flipperTalon.overrideSoftLimitsEnable(true);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
