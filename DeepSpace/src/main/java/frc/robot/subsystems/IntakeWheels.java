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

public class IntakeWheels extends Subsystem {

  private static final TalonSRX intakeWheels = new TalonSRX(RobotMap.TalonIntakeWheels);
  private static IntakeWheels instance;

  private IntakeWheels(){
    init();
  }

  private void init(){

    intakeWheels.configFactoryDefault();
    intakeWheels.set(ControlMode.PercentOutput,0);
    intakeWheels.setNeutralMode(NeutralMode.Brake);

    intakeWheels.configNominalOutputForward(0,30);
    intakeWheels.configNominalOutputReverse(0,30);
    intakeWheels.configPeakOutputForward(1, 30);
    intakeWheels.configPeakOutputReverse(-1, 30);
    
    intakeWheels.configPeakCurrentLimit(15, 30);
    intakeWheels.configPeakCurrentDuration(120, 30);
    intakeWheels.configContinuousCurrentLimit(1, 30);
    intakeWheels.enableCurrentLimit(true);

  }

  public static IntakeWheels getInstance(){
    if (instance==null){
      instance = new IntakeWheels();
    }
    return instance;
  }

  public void injest(){
    intakeWheels.set(ControlMode.PercentOutput, -0.5);
  }

  public void eject(){
    intakeWheels.set(ControlMode.PercentOutput, 1.0);
  }

  public void stop(){
    intakeWheels.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
