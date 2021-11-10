/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class IntakeWheels extends SubsystemBase {

  private static final TalonSRX talon = new TalonSRX(RobotMap.IntakeWheelsTalon); 
  private final PowerDistributionPanel pdp = new PowerDistributionPanel(RobotMap.PDPID);
  private double intakeJamThreshold = 65;
  private XboxController m_AssistController;
  private boolean wheelsOn;
  /**
   * Creates a new IntakeWheels.
   */ 
  public IntakeWheels(XboxController assist) {
    talon.configFactoryDefault();
    talon.configContinuousCurrentLimit(18);
    talon.configPeakCurrentLimit(30);
    talon.configPeakCurrentDuration(200);
    talon.enableCurrentLimit(true);
    m_AssistController = assist;
    wheelsOn = false; 
  }

@Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Intake current", this.pdp.getCurrent(RobotMap.pdpIntake));
    this.unJamIntake();
   
  }

  public void on(){
    talon.set(ControlMode.PercentOutput, 0.85);
    wheelsOn = true;
  }

  public void reverse(){
    talon.set(ControlMode.PercentOutput, -0.85);
  }

  public void off(){
    talon.set(ControlMode.PercentOutput, 0.0); 
    wheelsOn = false;
  }

  public void returnToPrevState(){
    if(wheelsOn){
      this.on();
    }
    else{
      this.off();
    }
  }

  private void unJamIntake(){
    //this will run if intake amps is above 30 - 1 or more balls are jammed
    //and warn the assistant by rumbling the joystick and then go sentiant 
    double intakeCurrent = pdp.getCurrent(RobotMap.pdpIntake);
    m_AssistController.setRumble(RumbleType.kLeftRumble, 0);
    m_AssistController.setRumble(RumbleType.kRightRumble, 0);

    if (intakeCurrent > intakeJamThreshold){
      m_AssistController.setRumble(RumbleType.kLeftRumble, 1);
      m_AssistController.setRumble(RumbleType.kRightRumble, 1);

      // talon.set(ControlMode.PercentOutput, -0.5);
      // Timer.delay(0.6);
      // talon.set(ControlMode.PercentOutput, 0.0);
    }
  }
}
