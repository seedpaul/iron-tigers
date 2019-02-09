/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import frc.robot.RobotMap;
import frc.robot.commands.ArcadeDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem implements PIDOutput{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final WPI_VictorSPX frontRight = new WPI_VictorSPX(RobotMap.SPXFrontRight);
  private final WPI_VictorSPX frontLeft = new WPI_VictorSPX(RobotMap.SPXFrontLeft);
  private final WPI_TalonSRX backRight = new WPI_TalonSRX(RobotMap.SRXBackRight);
  private final WPI_TalonSRX backLeft = new WPI_TalonSRX(RobotMap.SRXBackLeft);

  private final SpeedControllerGroup rightSCG = new SpeedControllerGroup(frontRight, backRight);
  private final SpeedControllerGroup leftSCG = new SpeedControllerGroup(frontLeft, backLeft);

  private final DifferentialDrive robotDrive = new DifferentialDrive(leftSCG, rightSCG);

  private final Encoder leftEncoder = new Encoder(RobotMap.leftEncoderChannelA, RobotMap.leftEncoderChannelB, true, Encoder.EncodingType.k1X);
  private final Encoder rightEncoder = new Encoder(RobotMap.rightEncoderChannelA, RobotMap.rightEncoderChannelB, false, Encoder.EncodingType.k1X);

  private AHRS ahrs;
  private PIDController turnController;
  private double rotateToAngleRate;

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
    robotDrive.setExpiration(1);
    robotDrive.setSafetyEnabled(true);
    double circumference = 18.8496;
    double pulsesPerRevolution = 360;
    double distancePerPulse = circumference/pulsesPerRevolution;
    leftEncoder.setDistancePerPulse(distancePerPulse);
    rightEncoder.setDistancePerPulse(distancePerPulse);
    leftEncoder.reset();
    rightEncoder.reset();

    try {
        ahrs = new AHRS(SPI.Port.kMXP); 
    } catch (RuntimeException ex ) {
        DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
    }

    turnController = new PIDController(kP, kI, kD, kF, ahrs, this);
    turnController.setInputRange(-180.0f,  180.0f);
    turnController.setOutputRange(-1, 1);
    turnController.setAbsoluteTolerance(kToleranceDegrees);
    turnController.setContinuous(true);

    turnController.disable();
    
    /* Add the PID Controller to the Test-mode dashboard, allowing manual  */
    /* tuning of the Turn Controller's P, I and D coefficients.            */
    /* Typically, only the P value needs to be modified.                   */
    LiveWindow.addActuator("DriveSystem", "RotateController", turnController);
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

  private void addInfoToDashboard(){
    SmartDashboard.putData("leftEncoder", leftEncoder);
    SmartDashboard.putData("rightEncoder", rightEncoder);

  }

  public void resetEncoders(){
    leftEncoder.reset();
    rightEncoder.reset();
  }

  @Override
  /* This function is invoked periodically by the PID Controller, */
  /* based upon navX MXP yaw angle input and PID Coefficients.    */
  public void pidWrite(double output) {
      rotateToAngleRate = output;
      updateBot();
  }

  private void updateBot(){

    if(counter % 250 == 0){
      robotDrive.curvatureDrive(0.0, rotateToAngleRate, true);
    }
  }

  public void turn90(Joystick driver){
    turnController.setSetpoint(90.0f);
    applyPID(driver);
  }

  public void turn180(Joystick driver){
    turnController.setSetpoint(179.9f);
    applyPID(driver);
  }

  public void turn270(){
    turnController.setSetpoint(269.9f);
    applyPID();
  }

  public void turn360(){
    turnController.setSetpoint(359.9f);
    applyPID();
  }

  private void applyPID(Joystick driver){
    
    System.out.println("applyPid:" + rotateToAngleRate);
    robotDrive.curvatureDrive(-driver.getRawAxis(RobotMap.leftStickY), rotateToAngleRate, true);
    System.out.println("rotateToAngleRate:" + rotateToAngleRate);
    //Timer.delay(0.4);
  }

  private void applyPID(){
    
    System.out.println("applyPid:" + rotateToAngleRate);
    robotDrive.curvatureDrive(0.0, rotateToAngleRate, true);
    System.out.println("rotateToAngleRate:" + rotateToAngleRate);
    Timer.delay(0.4);
  }

  public void resetAHRS(){
    ahrs.reset();
  }

  public void enablePID(){
    turnController.enable();
    System.out.println("PID Enabled");
  };

  public void disablePID(){
    turnController.disable();
    System.out.println("PID Disabled");
  };

}
