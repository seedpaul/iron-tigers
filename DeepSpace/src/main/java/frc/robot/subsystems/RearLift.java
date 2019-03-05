/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.RobotMap;
import frc.robot.subsystems.positioning.*;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class RearLift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static TalonSRX rearLiftSRX = new TalonSRX(RobotMap.SRXRearLift);

  private static RearLift instance;

  private RearLift(){
    init();
  }

  //this is where we do all our intilization
  private void init(){

    rearLiftSRX.configFactoryDefault();

    rearLiftSRX.set(ControlMode.PercentOutput,0);
    rearLiftSRX.setNeutralMode(NeutralMode.Brake);

    //this is a very important line of code in order to make foward on motor line up with foward on encoder
    rearLiftSRX.setSensorPhase(false);

    rearLiftSRX.configForwardSoftLimitEnable(true);
    rearLiftSRX.configReverseSoftLimitEnable(true);

    rearLiftSRX.configForwardSoftLimitThreshold(RearLiftPositions.getHomePosition());
    rearLiftSRX.configReverseSoftLimitThreshold(RearLiftPositions.getHighestPosition());

    rearLiftSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    
    rearLiftSRX.configNominalOutputForward(0,30);
    rearLiftSRX.configNominalOutputReverse(0,30);
    rearLiftSRX.configPeakOutputForward(1, 30);
    rearLiftSRX.configPeakOutputReverse(-1, 30);

    rearLiftSRX.configAllowableClosedloopError(0, 0, 30);

		rearLiftSRX.config_kF(0, 0.0, 30);
		rearLiftSRX.config_kP(0, 1.0, 30);
		rearLiftSRX.config_kI(0, 0.0, 30);
    rearLiftSRX.config_kD(0, 1.0, 30);

    rearLiftSRX.configPeakCurrentLimit(35, 30);
    rearLiftSRX.configPeakCurrentDuration(120, 30);
    rearLiftSRX.configContinuousCurrentLimit(25, 30);
    rearLiftSRX.enableCurrentLimit(true);

    rearLiftSRX.setSelectedSensorPosition(RearLiftPositions.rear_home,0,30);
  }

  public static RearLift getInstance(){
    if (instance == null){
      instance = new RearLift();
    }
    return instance;
  }

  public void goToLevel6(){
    setPosition(RearLiftPositions.rear_habLevel6);
  }

  public void goToLevel19(){
    setPosition(RearLiftPositions.rear_habLevel19);
  }

  public void goToHome(){
    setPosition(RearLiftPositions.rear_home);
  }

  private void setPosition(int position){
    rearLiftSRX.set(ControlMode.Position, position);
    rearLiftSRX.getSelectedSensorPosition();
  }

  public int getSensorValue(){
    return rearLiftSRX.getSelectedSensorPosition();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
