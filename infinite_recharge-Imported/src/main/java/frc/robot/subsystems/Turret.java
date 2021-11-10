/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import frc.robot.RobotMap;

public class Turret extends SubsystemBase {

  private final TalonSRX talon = new TalonSRX(RobotMap.TurretTalon); 

  // value that store the current position of turret
  private double current = 0;
  private boolean aquireTarget = false;

  private final double gr = 208/36; // gear ration 208/36
  private final double ppmr = 4096; //pulses per motor revolution (ctre mag encoder ppr 4096)
  private final double pptr = gr * ppmr; // pulses per turret revolution
  private final double dptp = pptr/360; // degrees per turret pulse
  private double m_xOffset = 0.0; // x offset reported by limelight
  private final int maxOffset = -6000;// Maximum x offset allow
  private final int tolerance = 65;

  public Turret() {
    //pulse per revolution = 4096
    talon.configFactoryDefault();
    talon.set(ControlMode.Position, 0);
    talon.setNeutralMode(NeutralMode.Brake);

    //this is a very important line of code
    talon.setSensorPhase(false);
    talon.setInverted(false);
    talon.configVoltageCompSaturation(12);
    talon.enableVoltageCompensation(true);

    talon.configForwardSoftLimitEnable(true);
    talon.configReverseSoftLimitEnable(true);

    talon.configForwardSoftLimitThreshold(-maxOffset);
    talon.configReverseSoftLimitThreshold(maxOffset);

    talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);

    talon.configNominalOutputForward(0,30);
    talon.configNominalOutputReverse(0,30);
    talon.configPeakOutputForward(1, 30);
    talon.configPeakOutputReverse(-1, 30);

    talon.configAllowableClosedloopError(0,40,30);

		talon.config_kF(0, 0.0, 30);
		talon.config_kP(0, 1.5, 30);
		talon.config_kI(0, 0.0, 30);
    talon.config_kD(0, 0.0, 30);

    talon.configPeakCurrentLimit(40 , 30);
    talon.configPeakCurrentDuration(120, 30);
    talon.configContinuousCurrentLimit(15, 30);
    talon.enableCurrentLimit(true);

    //pre-flight checklist to make sure turrret is face directly backwards
    talon.setSelectedSensorPosition(0,0,30);
  
    SmartDashboard.putBoolean("onTarget", false);
    SmartDashboard.putBoolean("Aquire Target", false);
  }

  @Override
  public void periodic() {

    SmartDashboard.putBoolean("onTarget", this.onTarget());
    SmartDashboard.putBoolean("Aquire Target", this.aquireTarget);

    if(aquireTarget){//Bombardier notified turrent to target
      // TrackTarget returns the offset to the target in degrees
      // if limelight has a valid target, if no valid target is found
      // TrackTarget returns no offset(0.0)
      // 1.)add the offset to current position
      current = current + trackTarget();
      SmartDashboard.putNumber("Current", current);
      //Only apply changes that are less than 90 degrees off starting position
      //if target positions is greater than 90 return 90 with the proper sign(+/-)
      //current = Math.abs(current) <= maxOffset ? current : (Math.signum(current) * maxOffset);
      // 2.) update pid setpoint to new position
      talon.set(ControlMode.Position, current);
      
      // 4.) update current positoin to position after adjustment and delay
      current = talon.getSelectedSensorPosition(0);
    }
    SmartDashboard.putNumber("Current", current);
  }
  // OI function *******************************************************************
  public void targetingEnabled(double in_XOffset){
    //Turret will auto-aim towards target
    this.aquireTarget = true;
    //get x offset from limelight
    m_xOffset = in_XOffset;
    SmartDashboard.putNumber("m_xOffset", m_xOffset);
  }

  public void targetingDisabled(){
    //Turret will stop auto-aiming towards target
    // and return to center back position
    this.aquireTarget = false;
    
    //reset x offset
    m_xOffset = 0.0;

    //recenter turret on back of robot
    current = 0;
    talon.set(ControlMode.Position, current);
  }
  // end OI functions *******************************************************************


  // vision functions *******************************************************************
  private double trackTarget()
  {
    SmartDashboard.putNumber("m_xOffset * dptp", m_xOffset * dptp);
    // TrackTarget returns the offset to the target in turret pulses (+/-)  
    return m_xOffset * dptp;
    
  }

  public boolean onTarget(){
    // is the pid reporting that on the setpoint within the tolerance
    return Math.abs(talon.getClosedLoopError()) < tolerance;
  }

  // end vision functions *******************************************************************

}
