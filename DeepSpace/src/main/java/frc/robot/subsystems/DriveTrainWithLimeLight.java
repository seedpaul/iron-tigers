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
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.networktables.*;

public class DriveTrainWithLimeLight extends Subsystem{

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

  private static DriveTrainWithLimeLight instance;

  static final double kP = 0.012;
  static final double kI = 0.00;
  static final double kD = 0.00;
  static final double kF = 0.00;
  static final double kToleranceDegrees = 2.0f;

  private boolean m_LimelightHasValidTarget = false;
  private double m_LimelightDriveCommand = 0.0;
  private double m_LimelightSteerCommand = 0.0;

  int counter = 0;

  private DriveTrainWithLimeLight(){
    init();
  }

  public static DriveTrainWithLimeLight getInstance(){
    if (instance == null){
      instance = new DriveTrainWithLimeLight();
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
  }

  public void autoDrive(Joystick driver) {
    Update_Limelight_Tracking();

    if (m_LimelightHasValidTarget)
    {
      robotDrive.arcadeDrive(m_LimelightDriveCommand,m_LimelightSteerCommand);
    }
    else
    {
      robotDrive.arcadeDrive(0.0,0.0);
    }

  }

  public void brake(){
    backRightTalon.setNeutralMode(NeutralMode.Brake);
    backLeftTalon.setNeutralMode(NeutralMode.Brake);
  }

  public void Update_Limelight_Tracking()
  {
        // These numbers must be tuned for your Robot!  Be careful!
        final double STEER_K = 0.03;                    // how hard to turn toward the target
        final double DRIVE_K = 0.26;                    // how hard to drive fwd toward the target
        final double DESIRED_TARGET_AREA = 13.0;        // Area of the target when the robot reaches the wall
        final double MAX_DRIVE = 0.7;                   // Simple speed limit so we don't drive too fast

        double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
        double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
        double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

        if (tv < 1.0)
        {
          m_LimelightHasValidTarget = false;
          m_LimelightDriveCommand = 0.0;
          m_LimelightSteerCommand = 0.0;
          return;
        }

        m_LimelightHasValidTarget = true;

        // Start with proportional steering
        double steer_cmd = tx * STEER_K;
        m_LimelightSteerCommand = steer_cmd;

        // try to drive forward until the target area reaches our desired area
        double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

        // don't let the robot drive too fast into the goal
        if (drive_cmd > MAX_DRIVE)
        {
          drive_cmd = MAX_DRIVE;
        }
        m_LimelightDriveCommand = drive_cmd;
  }

}
