package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.subsystems.positioning.IntakeElbowPositions;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Elbow extends Subsystem {

  private static final int deviceID = 13;
  private CANPIDController PIDcontroller;
  private CANEncoder encoder;
  private CANSparkMax sparkMax;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

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
    sparkMax = new CANSparkMax(deviceID,MotorType.kBrushless);
    sparkMax.restoreFactoryDefaults();

    PIDcontroller = sparkMax.getPIDController();
    encoder = sparkMax.getEncoder();

    // PID coefficients
    kP = 1.8; 
    kI = 0.0;
    kD = 1.0; 
    kIz = 0; 
    kFF = 0; 
    kMaxOutput = 1; 
    kMinOutput = -1;

    // set PID coefficients
    PIDcontroller.setP(kP);
    PIDcontroller.setI(kI);
    PIDcontroller.setD(kD);
    PIDcontroller.setIZone(kIz);
    PIDcontroller.setFF(kFF);
    PIDcontroller.setOutputRange(kMinOutput, kMaxOutput);

    encoder.setPosition(0);
  }

  public void up(){
    setPosition(IntakeElbowPositions.up());
  }

  public void down(){
    setPosition(IntakeElbowPositions.down());
  }

  private void setPosition(int position){
    PIDcontroller.setReference(position, ControlType.kPosition);
    SmartDashboard.putNumber("elbow target position",position);
  }

  public void stop(){

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public void periodic() {
    super.periodic();
    SmartDashboard.putNumber("elbow position",encoder.getPosition());
    SmartDashboard.putNumber("elbow velocity",encoder.getVelocity());
  }
}
