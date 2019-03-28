package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.subsystems.positioning.IntakeElbowPositions;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class Elbow extends Subsystem {
  
  private static TalonSRX intakeElbowTalon = new TalonSRX(RobotMap.TalonIntakeElbow);
  private static Elbow instance;

  private Elbow(){
    init();
  }

  public static Elbow getInstance() {
    if(instance == null){
      instance = new Elbow();
    }
    return instance;
  }

  private void init(){

    //pulse per revolution = 4096
    intakeElbowTalon.configFactoryDefault();
    intakeElbowTalon.set(ControlMode.PercentOutput,0);
    intakeElbowTalon.setNeutralMode(NeutralMode.Brake);

    //this is a very important line of code
    intakeElbowTalon.setSensorPhase(true);

    intakeElbowTalon.configForwardSoftLimitEnable(true);
    intakeElbowTalon.configReverseSoftLimitEnable(true);

    intakeElbowTalon.configForwardSoftLimitThreshold(IntakeElbowPositions.getMax());
    intakeElbowTalon.configReverseSoftLimitThreshold(IntakeElbowPositions.getMin());

    intakeElbowTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    
    intakeElbowTalon.configNominalOutputForward(0,30);
    intakeElbowTalon.configNominalOutputReverse(0,30);
    intakeElbowTalon.configPeakOutputForward(0.9, 30);
    intakeElbowTalon.configPeakOutputReverse(-0.8, 30);

    intakeElbowTalon.configAllowableClosedloopError(0,75,30);

		intakeElbowTalon.config_kF(0, 0.0, 30);
		intakeElbowTalon.config_kP(0, 1.5, 30);
		intakeElbowTalon.config_kI(0, 0.0, 30);
    intakeElbowTalon.config_kD(0, 10.0, 30);

    intakeElbowTalon.configPeakCurrentLimit(16 , 30);
    intakeElbowTalon.configPeakCurrentDuration(120, 30);
    intakeElbowTalon.configContinuousCurrentLimit(1, 30);
    intakeElbowTalon.enableCurrentLimit(true);

    //pre-flight checklist to make sure elbow is all the way @ up
    intakeElbowTalon.setSelectedSensorPosition(IntakeElbowPositions.getMax(),0,30);
  }

  public void up(){
    setPosition(IntakeElbowPositions.up());
  }

  public void down(){
    setPosition(IntakeElbowPositions.down());
  }

  private void setPosition(int position){
    intakeElbowTalon.set(ControlMode.Position, position);
  }

  public void stop(){
    intakeElbowTalon.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
