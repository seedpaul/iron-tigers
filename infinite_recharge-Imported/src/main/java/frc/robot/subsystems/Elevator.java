/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class Elevator extends SubsystemBase {

  private double kP, kI, kD, kF;
  private int curr_position;
  //private boolean okToDescend, decsending;
  private static final TalonSRX elevatorLeftTalon = new TalonSRX(RobotMap.ElevatorLeftTalon);
  private static final TalonSRX elevatorRightTalon = new TalonSRX(RobotMap.ElevatorRightTalon);

  /**
   * Creates a new Elevator.
   */
  public Elevator() {

    //okToDescend = false;
    //decsending = false;
    kP = 0.025;
    kI = 0;
    kD = 0;
    kF = 0;

    elevatorLeftTalon.configFactoryDefault();
    elevatorRightTalon.configFactoryDefault();

    elevatorLeftTalon.setInverted(true);
    elevatorLeftTalon.setSensorPhase(false);
    elevatorRightTalon.setInverted(true);
    elevatorRightTalon.setSensorPhase(false);

    // right(3) should follow left(5)
    elevatorRightTalon.follow(elevatorLeftTalon);

    elevatorLeftTalon.set(ControlMode.Position, 0);
    elevatorLeftTalon.setNeutralMode(NeutralMode.Brake);

    elevatorLeftTalon.configVoltageCompSaturation(12);
    elevatorLeftTalon.enableVoltageCompensation(true);

    elevatorRightTalon.configVoltageCompSaturation(12);
    elevatorRightTalon.enableVoltageCompensation(true);

    elevatorLeftTalon.configClosedloopRamp(0.25, 30);

    // elevatorLeftTalon.configForwardSoftLimitThreshold(Constants.ElevatorPositions.Top);
    // elevatorLeftTalon.configReverseSoftLimitThreshold(Constants.ElevatorPositions.Home);

    // elevatorLeftTalon.configForwardSoftLimitEnable(true);
    // elevatorLeftTalon.configReverseSoftLimitEnable(true);

    elevatorLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);

    elevatorLeftTalon.configNominalOutputForward(0, 30);
    elevatorLeftTalon.configNominalOutputReverse(0, 30);
    elevatorLeftTalon.configPeakOutputForward(1, 30);
    elevatorLeftTalon.configPeakOutputReverse(-1, 30);

    elevatorLeftTalon.configPeakCurrentLimit(70, 30);
    elevatorLeftTalon.configPeakCurrentDuration(120, 30);
    elevatorLeftTalon.configContinuousCurrentLimit(15, 30);
    elevatorLeftTalon.enableCurrentLimit(true);

    elevatorLeftTalon.configAllowableClosedloopError(0, 0, 30);

    elevatorLeftTalon.config_kF(0, kF, 30);
    elevatorLeftTalon.config_kP(0, kP, 30);
    elevatorLeftTalon.config_kI(0, kI, 30);
    elevatorLeftTalon.config_kD(0, kD, 30);

    // //pre-flight checklist to make sure elevator is all the way @ bottom
    curr_position = Constants.ElevatorPositions.Home;
    elevatorLeftTalon.setSelectedSensorPosition(Constants.ElevatorPositions.Home, 0, 30);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //okToDescend = (curr_position == Constants.ElevatorPositions.Top && Math.abs(elevatorLeftTalon.getClosedLoopError()) < 3000);
  }

  public void up() {
    curr_position = Constants.ElevatorPositions.Top;
    this.setPosition(Constants.ElevatorPositions.Top);
  }

  public void down() {
    //if(okToDescend || decsending){
      //decsending = true;
      curr_position -= Constants.ElevatorPositions.Increment;
      if(curr_position > Constants.ElevatorPositions.Increment){
        this.setPosition(curr_position);
      }
      else{
        curr_position = Constants.ElevatorPositions.Increment; 
        this.setPosition(curr_position);
      }
    //}
  }

  private void setPosition(int position) {
    elevatorLeftTalon.set(ControlMode.Position, position, DemandType.ArbitraryFeedForward, 0.0);
  }

  public void stop() {
    // elevatorTalon.set(ControlMode.PercentOutput, 0.0);
  }

}