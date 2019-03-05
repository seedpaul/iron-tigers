package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.subsystems.positioning.ElevatorPositions;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;


public class Elevator extends Subsystem {

  private static TalonSRX elevatorTalon = new TalonSRX(RobotMap.TalonElevator);
  private static VictorSPX elevatorVictor = new VictorSPX(RobotMap.VictorElevator);

  private static Elevator instance;

  private Elevator(){
    init();
  }

  public static Elevator getInstance() {
    if(instance == null){
      instance = new Elevator();
    }
    return instance;
  }

  private void init(){

    elevatorTalon.configFactoryDefault();
    elevatorVictor.configFactoryDefault();

    elevatorTalon.set(ControlMode.PercentOutput,0);
    elevatorVictor.set(ControlMode.Follower,0);

    elevatorTalon.setNeutralMode(NeutralMode.Brake);
    elevatorVictor.setNeutralMode(NeutralMode.Brake);

    //this is a very important line of code
    elevatorTalon.setSensorPhase(true);

    elevatorTalon.configForwardSoftLimitEnable(true);
    elevatorTalon.configReverseSoftLimitEnable(true);

    elevatorTalon.configForwardSoftLimitThreshold(ElevatorPositions.getHighestPosition());
    elevatorTalon.configReverseSoftLimitThreshold(ElevatorPositions.getHomePosition());

    elevatorTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    
    elevatorTalon.configNominalOutputForward(0,30);
    elevatorTalon.configNominalOutputReverse(0,30);
    elevatorTalon.configPeakOutputForward(1, 30);
    elevatorTalon.configPeakOutputReverse(-0.5, 30);

    elevatorTalon.configAllowableClosedloopError(0, 0, 30);

		elevatorTalon.config_kF(0, 0.0, 30);
		elevatorTalon.config_kP(0, 0.5, 30);
		elevatorTalon.config_kI(0, 0.0, 30);
    elevatorTalon.config_kD(0, 1.0, 30);

    elevatorVictor.follow(elevatorTalon);

    //pre-flight checklist to make sure elevator is all the way @ bottom
    elevatorTalon.setSelectedSensorPosition(0,0,30);
  }

  public void up(){
    setPosition(ElevatorPositions.up());
  }

  public void down(){
    setPosition(ElevatorPositions.down());
  }

  public void goToHome(){
    setPosition(ElevatorPositions.home);
  }

  private void setPosition(int position){
    elevatorTalon.set(ControlMode.Position, position);
  }

  public void stop(){
    elevatorTalon.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
