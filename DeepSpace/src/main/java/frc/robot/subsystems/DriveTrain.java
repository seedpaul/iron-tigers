/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.SRXFrontRight);
  private final WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.SRXFrontLeft);
  private final WPI_TalonSRX backRight = new WPI_TalonSRX(RobotMap.SRXBackRight);
  private final WPI_TalonSRX backLeft = new WPI_TalonSRX(RobotMap.SRXBackLeft);

  private final SpeedControllerGroup rightSCG = new SpeedControllerGroup(frontRight, backRight);
  private final SpeedControllerGroup leftSCG = new SpeedControllerGroup(frontLeft, backLeft);

  private final DifferentialDrive robotDrive = new DifferentialDrive(leftSCG, rightSCG);

  private final Encoder leftEncoder = new Encoder(RobotMap.leftEncoderChannelA, RobotMap.leftEncoderChannelB, true, Encoder.EncodingType.k4X);
  private final Encoder rightEncoder = new Encoder(RobotMap.rightEncoderChannelA, RobotMap.rightEncoderChannelB, false, Encoder.EncodingType.k4X);

  //TODO:we need a gyro or the navX 

  private static DriveTrain instance;

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
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
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
}
