/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class HatchPanelFlipper extends Subsystem {

  private static final TalonSRX intakeflipper = new TalonSRX(RobotMap.TalonIntakeFlipper);

  private static final int flipper_home = 0;
  private static final int flipper_close = 1100;

  private static HatchPanelFlipper instance;

  private HatchPanelFlipper(){
    init();
  }

  public static HatchPanelFlipper getInstance(){
    if (instance==null){
      instance = new HatchPanelFlipper();
    }
    return instance;
  }
  
  private void init(){

    intakeflipper.configFactoryDefault();
    intakeflipper.set(ControlMode.PercentOutput,0);
    intakeflipper.setNeutralMode(NeutralMode.Brake);

    intakeflipper.configForwardSoftLimitEnable(true);
    intakeflipper.configReverseSoftLimitEnable(true);

    intakeflipper.configForwardSoftLimitThreshold(flipper_close);
    intakeflipper.configReverseSoftLimitThreshold(flipper_home);

    intakeflipper.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);

    intakeflipper.config_kF(0, 0.0, 30);
		intakeflipper.config_kP(0, 1.0, 30);
		intakeflipper.config_kI(0, 0.0, 30);
    intakeflipper.config_kD(0, 1.0, 30);
    
    intakeflipper.configAllowableClosedloopError(0,7,30);

    intakeflipper.configNominalOutputForward(0,30);
    intakeflipper.configNominalOutputReverse(0,30);
    intakeflipper.configPeakOutputForward(1, 30);
    intakeflipper.configPeakOutputReverse(-1, 30);

    intakeflipper.configPeakCurrentLimit(15, 30);
    intakeflipper.configPeakCurrentDuration(60, 30);
    intakeflipper.configContinuousCurrentLimit(1, 30);

    intakeflipper.enableCurrentLimit(true);

    intakeflipper.setSelectedSensorPosition(flipper_home,0,30);
  }

  public void homeFlipper(){
    intakeflipper.set(ControlMode.Position, flipper_home);
  }

  public void closeFlipper(){
    intakeflipper.set(ControlMode.Position, flipper_close);
  }

  public void stopFlipper(){
    intakeflipper.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
