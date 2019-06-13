package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.RobotMap;
import frc.robot.subsystems.positioning.FrontLiftPositions;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FrontLift extends Subsystem {

  private final TalonSRX liftTalon = new TalonSRX(RobotMap.TalonFrontLift);
  private final VictorSPX liftVictor = new VictorSPX(RobotMap.VictorFrontLift);

  private static FrontLift instance;

  private FrontLift(){
    init();
  }

  public static FrontLift getInstance(){
    if(instance == null){
      instance = new FrontLift();
    }
    return instance;
  }

  private void init(){

    liftTalon.configFactoryDefault();
    liftVictor.configFactoryDefault();

    liftTalon.set(ControlMode.PercentOutput,0);
    liftVictor.set(ControlMode.Follower,0);

    liftTalon.setNeutralMode(NeutralMode.Brake);
    liftVictor.setNeutralMode(NeutralMode.Brake);

    //this is a very important line of code
    liftTalon.setSensorPhase(false);

    liftTalon.configForwardSoftLimitEnable(true);
    liftTalon.configReverseSoftLimitEnable(true);

    liftTalon.configForwardSoftLimitThreshold(FrontLiftPositions.getHomePosition());
    liftTalon.configReverseSoftLimitThreshold(FrontLiftPositions.geLowestPosition());

    liftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    
    liftTalon.configNominalOutputForward(0,30);
    liftTalon.configNominalOutputReverse(0,30);
    liftTalon.configPeakOutputForward(1.0, 30);
    liftTalon.configPeakOutputReverse(-1.0, 30);

    liftTalon.configAllowableClosedloopError(0, 0, 30);

		liftTalon.config_kF(0, 0.0, 30);
		liftTalon.config_kP(0, 1.0, 30);
		liftTalon.config_kI(0, 0.0, 30);
    liftTalon.config_kD(0, 1.0, 30);

    liftTalon.configPeakCurrentLimit(60, 30);
    liftTalon.configPeakCurrentDuration(900, 30);
    liftTalon.configContinuousCurrentLimit(30, 30);
    liftTalon.enableCurrentLimit(true);

    liftVictor.follow(liftTalon);
    
    //pre-flight checklist to make sure front lift is all the way up
    liftTalon.setSelectedSensorPosition(FrontLiftPositions.getHomePosition(),0,30);
    //liftTalon.setSelectedSensorPosition(140000,0,30);

    
    //liftTalon.setSelectedSensorPosition(FrontLiftPositions.front_habLevel19,0,30);

    //liftTalon.setSelectedSensorPosition(FrontLiftPositions.geLowestPosition(),0,30);
    //liftTalon.setSelectedSensorPosition(145000,0,30);
  }

  public void climbStep2(){
    setPosition(FrontLiftPositions.front_habClimbStep2);
  }

  public void climbStep1(){
    setPosition(FrontLiftPositions.front_habClimbStep1);
  }

  public void gotoToLevel6position(){
    setPosition(FrontLiftPositions.front_habLevel6);
  }

  public void goToLevel19Position(){
    setPosition(FrontLiftPositions.front_habLevel19);
  }

  public void goToHome(){
    setPosition(FrontLiftPositions.getHomePosition());
  }

  private void setPosition(int position){
    liftTalon.set(ControlMode.Position, position);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

