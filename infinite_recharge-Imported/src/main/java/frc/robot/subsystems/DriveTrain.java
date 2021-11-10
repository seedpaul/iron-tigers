/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.subsystems.components.NavX;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */

   //declare the motor crontrollers
   public final WPI_TalonFX frontRightTalon = new WPI_TalonFX(RobotMap.FrontRightTalon);
   public final WPI_TalonFX frontLeftTalon = new WPI_TalonFX(RobotMap.FrontLeftTalon);
   public final WPI_TalonFX backRightTalon = new WPI_TalonFX(RobotMap.BackRightTalon);
   public final WPI_TalonFX backLeftTalon = new WPI_TalonFX(RobotMap.BackLeftTalon);
   public final WPI_TalonFX topRightTalon = new WPI_TalonFX(RobotMap.TopRightTalon);
   public final WPI_TalonFX topLeftTalon = new WPI_TalonFX(RobotMap.TopLeftTalon);
   private NavX m_NavX;
  
    private double circumference = 18.8496;                               
    private double pulsesPerRevolution = 2048;
    private double finalGearRatio = 8.4;
    private double distancePerPulse = circumference/(pulsesPerRevolution * finalGearRatio);

   //groups the motor controllers into left and right groups
   private final SpeedControllerGroup rightSCG = new SpeedControllerGroup(frontRightTalon, backRightTalon, topRightTalon);
   private final SpeedControllerGroup leftSCG = new SpeedControllerGroup(frontLeftTalon, backLeftTalon, topLeftTalon);

   //declares the drive train (which consists of each motor controller)
   public final DifferentialDrive robotDrive = new DifferentialDrive(leftSCG, rightSCG);

   public DriveTrain(NavX in_navX){
     
    m_NavX = in_navX;
    
    frontRightTalon.setNeutralMode(NeutralMode.Coast);
    frontLeftTalon.setNeutralMode(NeutralMode.Coast);
    backRightTalon.setNeutralMode(NeutralMode.Coast);
    backLeftTalon.setNeutralMode(NeutralMode.Coast);

    // braking only the two top motors gets us an appropriate
    // amount of braking
    topRightTalon.setNeutralMode(NeutralMode.Brake);
    topLeftTalon.setNeutralMode(NeutralMode.Brake);

    robotDrive.setExpiration(1);
    robotDrive.setSafetyEnabled(false);

    //supConfig values; true = current limiting is on, set to 40 amp max, motors can run at 60 apms for 1 second then falls back to 40 amps 
    SupplyCurrentLimitConfiguration supConfig = new SupplyCurrentLimitConfiguration(true, 40, 60, 1);
    frontRightTalon.configSupplyCurrentLimit(supConfig);
    frontLeftTalon.configSupplyCurrentLimit(supConfig);
    backRightTalon.configSupplyCurrentLimit(supConfig);
    backLeftTalon.configSupplyCurrentLimit(supConfig);
    topRightTalon.configSupplyCurrentLimit(supConfig);
    topLeftTalon.configSupplyCurrentLimit(supConfig);

    frontRightTalon.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 10);
    frontLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 10);
    backRightTalon.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 10);
    backLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 10);
    topRightTalon.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 10);
    topLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 10);

   }

   public void arcade(XboxController driver){

    double speed = -driver.getRawAxis(RobotMap.leftStickY);
    double turn = driver.getRawAxis(RobotMap.rightStickX);

    turn = turn * 0.55;
    speed = speed * 0.75;

    robotDrive.arcadeDrive(speed, turn, true);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    robotDrive.tankDrive(leftSpeed, rightSpeed);
  }

  public void autoDrive(double speed, double rotation, boolean quickTurn) {
    robotDrive.curvatureDrive(speed, rotation, quickTurn);
  }

  public void stop(){
    robotDrive.stopMotor();
  }

  public double getLeftEncoderDistance() 
    { //distance returned is in inches
    	return topLeftTalon.getSelectedSensorPosition(0) * distancePerPulse;
    }
    	
    public double getRightEncoderDistance() 
    { //distance returned is in inches
    	return topRightTalon.getSelectedSensorPosition(0) * distancePerPulse;
    }    

    public void resetLeftEncoder() 
    {
    	topLeftTalon.setSelectedSensorPosition(0, 0, 10);
    }

    public void resetRightEncoder() 
    {
      topRightTalon.setSelectedSensorPosition(0, 0, 10);    }
  
    public double getGyroAngle() 
    {
      //converts yaw(-180 to 180) to a value from -1 to 1
    	return m_NavX.getYaw();
    }
    
    public double getGyroAngleConverted() 
    {
      //converts yaw(-180 to 180) to a value from -1 to 1
    	return m_NavX.getYaw()/180;
    }

    public void resetGyro()
    {
    	m_NavX.reset();
    }
  

}
