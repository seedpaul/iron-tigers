package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.drivetrain.ArcadeDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

public class DriveTrain extends Subsystem{

  private final WPI_VictorSPX frontRightVictor = new WPI_VictorSPX(RobotMap.VictorFrontRight);
  private final WPI_VictorSPX frontLeftVictor = new WPI_VictorSPX(RobotMap.VictorFrontLeft);
  private final WPI_TalonSRX backRightTalon = new WPI_TalonSRX(RobotMap.TalonBackRight);
  private final WPI_TalonSRX backLeftTalon = new WPI_TalonSRX(RobotMap.TalonBackLeft);

  private final SpeedControllerGroup rightSCG = new SpeedControllerGroup(frontRightVictor, backRightTalon);
  private final SpeedControllerGroup leftSCG = new SpeedControllerGroup(frontLeftVictor, backLeftTalon);

  private final DifferentialDrive robotDrive = new DifferentialDrive(leftSCG, rightSCG);

  private final Encoder leftEncoder = new Encoder(RobotMap.leftEncoderChannelA, RobotMap.leftEncoderChannelB, true, Encoder.EncodingType.k1X);
  private final Encoder rightEncoder = new Encoder(RobotMap.rightEncoderChannelA, RobotMap.rightEncoderChannelB, false, Encoder.EncodingType.k1X);

  private AHRS ahrs;

  private static DriveTrain instance;

  static final double kP = 0.012;
  static final double kI = 0.00;
  static final double kD = 0.00;
  static final double kF = 0.00;
  static final double kToleranceDegrees = 2.0f;

  int counter = 0;

  private DriveTrain(){
    init();
  }

  public static DriveTrain getInstance(){
    if (instance == null){
      instance = new DriveTrain();
    }

    return instance;
  }

  private void init(){

    backRightTalon.setNeutralMode(NeutralMode.Brake);
    backLeftTalon.setNeutralMode(NeutralMode.Brake);
    frontLeftVictor.setNeutralMode(NeutralMode.Brake);
    frontRightVictor.setNeutralMode(NeutralMode.Brake);

    robotDrive.setExpiration(1);
    robotDrive.setSafetyEnabled(true);

    double circumference = 18.8496;
    double pulsesPerRevolution = 256;
    double distancePerPulse = circumference/pulsesPerRevolution;

    leftEncoder.setDistancePerPulse(distancePerPulse);
    rightEncoder.setDistancePerPulse(distancePerPulse);
    
    leftEncoder.setReverseDirection(false);
    rightEncoder.setReverseDirection(true);
    leftEncoder.reset();
    rightEncoder.reset();

    try {
        ahrs = new AHRS(SPI.Port.kMXP); 
    } catch (RuntimeException ex ) {
        DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
    }
    
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ArcadeDrive());
  }

  public void arcade(Joystick driver){

    double speed = -driver.getRawAxis(RobotMap.leftStickY);
    double turn = driver.getRawAxis(RobotMap.rightStickX);
    robotDrive.arcadeDrive(speed, turn, true);
    addInfoToDashboard();
  }

  public void autoDrive(double speed, double rotation) {
    addInfoToDashboard();
    robotDrive.curvatureDrive(speed, rotation, false);
  }

  private void addInfoToDashboard(){
    //SmartDashboard.putData("leftEncoder", leftEncoder);
    //SmartDashboard.putData("rightEncoder", rightEncoder);
  }

  public void brake(){
    backRightTalon.setNeutralMode(NeutralMode.Brake);
    backLeftTalon.setNeutralMode(NeutralMode.Brake);
  }

  public void resetEncoders(){
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public void resetAHRS(){
    ahrs.reset();
  }

  public void stop(){
    robotDrive.stopMotor();
  }
  
  public double getLeftEncoderDistance() 
  {
    return leftEncoder.getDistance();
  }
    
  public double getRightEncoderDistance() 
  {
    return rightEncoder.getDistance();
  }    
  
  public void resetRightEncoder() 
  {
    rightEncoder.reset();
  }
  
  public void resetLeftEncoder() 
  {
    leftEncoder.reset();
  }

  public double getAHRSAngle() 
  {
    return ahrs.getAngle();
  }

}
