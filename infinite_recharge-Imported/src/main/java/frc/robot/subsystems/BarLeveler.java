/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

import frc.robot.subsystems.components.*;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class BarLeveler extends PIDSubsystem {

  private final NavX m_NavX;

  private static final TalonSRX talon = new TalonSRX(RobotMap.BarLeverTalon); 
  /**
   * Creates a new BarLeveler.
   */
  public BarLeveler(NavX in_NavX) {

    super(
      // The PIDController used by the subsystem
      new PIDController(0.0, 0, 0.0, 0.01)
    );

    // turn on pid controller
    //this.enable();

    m_NavX = in_NavX;

    talon.configFactoryDefault();
    talon.configContinuousCurrentLimit(18);
    talon.configPeakCurrentLimit(30);
    talon.configPeakCurrentDuration(200);
    talon.enableCurrentLimit(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // PID functions *******************************************************************
  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
    talon.set(ControlMode.Current, output);
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    float value = m_NavX.getRoll();
    return value;
  }
  //end PID ************************************************************************


  public void move(double left_input, double right_input){
    double movement =  left_input + right_input;
    talon.set(ControlMode.PercentOutput ,movement);
  }
}
