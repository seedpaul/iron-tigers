package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.subsystems.positioning.IntakeElbowPositions;
import edu.wpi.first.wpilibj.smartdashboard.*;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class IntakeElbowSparkMax extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  private static CANSparkMax intakeElbowSparkMax; 
  private static CANPIDController pid_controller; 
  private static CANEncoder encoder;

  private static double kP = 0.1; 
  private static double kI = 1e-4;
  private static double kD = 1; 
  private static double kIz = 0; 
  private static double kFF = 0; 
  private static double kMaxOutput = 1; 
  private static double kMinOutput = -1;

  private static IntakeElbowSparkMax instance;

  private IntakeElbowSparkMax(){
    init();
  }

  private void init(){

      //rotations 72.0 = 90 degrees;
    intakeElbowSparkMax = new CANSparkMax(13, MotorType.kBrushless);
    pid_controller = intakeElbowSparkMax.getPIDController();
    encoder = intakeElbowSparkMax.getEncoder();

    intakeElbowSparkMax.restoreFactoryDefaults();

    intakeElbowSparkMax.setIdleMode(IdleMode.kBrake);
    intakeElbowSparkMax.setSmartCurrentLimit(20,80);
    intakeElbowSparkMax.setClosedLoopRampRate(0.025);
    intakeElbowSparkMax.setSecondaryCurrentLimit(82.5, 0);

    pid_controller.setP(kP);
    pid_controller.setI(kI);
    pid_controller.setD(kD);
    pid_controller.setIZone(kIz);
    pid_controller.setFF(kFF);
    pid_controller.setOutputRange(kMinOutput, kMaxOutput);


    encoder.setPositionConversionFactor(0.8);
    encoder.setPosition(0.0);
  }

  @Override
  public void periodic(){
    SmartDashboard.putNumber("Encoder Position", encoder.getPosition());
  }

  public static IntakeElbowSparkMax getInstance() {
    if(instance == null){
      instance = new IntakeElbowSparkMax();
    }
    return instance;
  }

  public void up(){
    pid_controller.setReference(0, ControlType.kPosition);
  }

  public void stop(){
    intakeElbowSparkMax.stopMotor();
  }

  public void down(){
    pid_controller.setReference(90, ControlType.kPosition);
  }

  public double getSensorPosition(){
    return encoder.getPosition();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
