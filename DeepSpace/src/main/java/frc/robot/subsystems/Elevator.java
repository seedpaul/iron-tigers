package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.subsystems.positioning.ElevatorPositions;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
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

    elevatorTalon.set(ControlMode.Position,0);
    elevatorVictor.set(ControlMode.Follower,0);

    elevatorTalon.setNeutralMode(NeutralMode.Brake);
    elevatorVictor.setNeutralMode(NeutralMode.Brake);

    //this is a very important line of code
    elevatorTalon.setSensorPhase(true);

    elevatorTalon.configClosedloopRamp(0.75, 30);

    elevatorTalon.configForwardSoftLimitEnable(true);
    elevatorTalon.configReverseSoftLimitEnable(true);

    elevatorTalon.configForwardSoftLimitThreshold(ElevatorPositions.getHighestPosition());
    elevatorTalon.configReverseSoftLimitThreshold(ElevatorPositions.getHomePosition());

    elevatorTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    
    elevatorTalon.configNominalOutputForward(0,30);
    elevatorTalon.configNominalOutputReverse(0,30);
    elevatorTalon.configPeakOutputForward(1.0, 30);
    elevatorTalon.configPeakOutputReverse(-0.7, 30);

    elevatorTalon.configPeakCurrentLimit(40 , 30);
    elevatorTalon.configPeakCurrentDuration(120, 30);
    elevatorTalon.configContinuousCurrentLimit(15, 30);
    elevatorTalon.enableCurrentLimit(true);

    elevatorTalon.configAllowableClosedloopError(0, 0, 30);


// For example if you want your mechanism to drive 50% throttle when the error is 4096 (one rotation when using CTRE Mag Encoder), 
// then the calculated Proportional Gain would be (0.50 X 1023) / 4096 = ~0.125.
// Tune this until the sensed value is close to the target under typical load. 
// Many prefer to simply double the P-gain until oscillations occur, then reduce accordingly.
// If the mechanism accelerates too abruptly, Derivative Gain can be used to smooth the motion. 
// Typically start with 10x to 100x of your current Proportional Gain. If application requires a 
// controlled (smooth) deceleration towards the target, we strongly recommend motion-magic.
// If the mechanism never quite reaches the target and increasing Integral Gain is viable, start with 1/100th of the Proportional Gain.

		elevatorTalon.config_kF(0, 0.0, 30);
		elevatorTalon.config_kP(0, 0.5, 30);
    elevatorTalon.config_kI(0, 0.0, 30);
    elevatorTalon.config_kD(0, 100.0, 30);

    elevatorVictor.follow(elevatorTalon);

    //pre-flight checklist to make sure elevator is all the way @ bottom
    elevatorTalon.setSelectedSensorPosition(0,0,30);
  }

  @Override
  public void periodic(){
    //SmartDashboard.putNumber("elevator",elevatorTalon.getSelectedSensorPosition(0));
  }

  public void up(){
    setPosition(ElevatorPositions.up());
  }

  public void down(){
    setPosition(ElevatorPositions.down());
  }

  public void upSimple(){
    setPosition(ElevatorPositions.upSimple());
  }

  public void downSimple(){
    setPosition(ElevatorPositions.downSimple());
  }

  public void goToHome(){
    setPosition(ElevatorPositions.home);
  }

  private void setPosition(int position){
    elevatorTalon.set(ControlMode.Position, position, DemandType.ArbitraryFeedForward,0.2);
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
