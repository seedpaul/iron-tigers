package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.IntakeElbowPositions;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class IntakeElbow extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  private static TalonSRX intakeElbowSRX = new TalonSRX(RobotMap.SRXIntakeElbow);

  private static IntakeElbow instance;

  private IntakeElbow(){
    init();
  }

  private void init(){

    //pulse per revolution = 4096

    intakeElbowSRX.configFactoryDefault();
    intakeElbowSRX.set(ControlMode.PercentOutput,0);
    intakeElbowSRX.setNeutralMode(NeutralMode.Brake);

    //this is a very important line of code
    intakeElbowSRX.setSensorPhase(true);

    intakeElbowSRX.configForwardSoftLimitEnable(true);
    intakeElbowSRX.configReverseSoftLimitEnable(true);

    intakeElbowSRX.configForwardSoftLimitThreshold(IntakeElbowPositions.getMax());
    intakeElbowSRX.configReverseSoftLimitThreshold(IntakeElbowPositions.getMin());

    intakeElbowSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    
    intakeElbowSRX.configNominalOutputForward(0,30);
    intakeElbowSRX.configNominalOutputReverse(0,30);
    intakeElbowSRX.configPeakOutputForward(0.8, 30);
    intakeElbowSRX.configPeakOutputReverse(-0.5, 30);

    intakeElbowSRX.configAllowableClosedloopError(0,75,30);

		intakeElbowSRX.config_kF(0, 0.0, 30);
		intakeElbowSRX.config_kP(0, 1.0, 30);
		intakeElbowSRX.config_kI(0, 0.0, 30);
    intakeElbowSRX.config_kD(0, 1.0, 30);

    intakeElbowSRX.configPeakCurrentLimit(32, 30);
    intakeElbowSRX.configPeakCurrentDuration(120, 30);
    intakeElbowSRX.configContinuousCurrentLimit(1, 30);
    intakeElbowSRX.enableCurrentLimit(true);

    //pre-flight checklist to make sure lift is all the way @ bottom
    intakeElbowSRX.setSelectedSensorPosition(IntakeElbowPositions.getMax(),0,30);

    //use this setting if we need to bump up the elbow to the home position
    //intakeElbowSRX.setSelectedSensorPosition(IntakeElbowPositions.getMax() - 500,0,30);
  }

  public static IntakeElbow getInstance() {
    if(instance == null){
      instance = new IntakeElbow();
    }
    return instance;
  }

  public void up(){
    intakeElbowSRX.set(ControlMode.Position, IntakeElbowPositions.up());
    intakeElbowSRX.getSelectedSensorPosition();
  }

  public void stop(){
    intakeElbowSRX.set(ControlMode.PercentOutput, 0.0);
  }

  public void down(){
    intakeElbowSRX.set(ControlMode.Position, IntakeElbowPositions.down());
    intakeElbowSRX.getSelectedSensorPosition();
  }

  public void bump(){
    /*********************** 
     * this is not used
    ************************/
    double currentSensorReading = intakeElbowSRX.getSelectedSensorPosition();
    intakeElbowSRX.set(ControlMode.Position, currentSensorReading + 1000);
  }

  public int getSensorPosition(){
    return intakeElbowSRX.getSelectedSensorPosition();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
