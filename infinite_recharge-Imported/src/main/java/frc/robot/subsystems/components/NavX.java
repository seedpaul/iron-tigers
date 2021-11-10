/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.components;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import com.kauailabs.navx.frc.AHRS;

public class NavX extends SubsystemBase {
  /**
   * Creates a new NavX.
      Axis	          Orientation           Linear motion	               Rotational Motion
      X (Pitch)	      Left/Right	          – Left / + Right	           + Tilt Backwards
      Y (Roll)	      Forward/Backward	    + Forward / – Backward	     + Roll Left
      Z (Yaw)	        Up/Down	              + Up / – Down	               + Clockwise/ – Counter-wise
   */

   private AHRS navX;

  public NavX() {
    
    try {
        navX = new AHRS(SerialPort.Port.kMXP);
      } catch (RuntimeException ex ) {
        DriverStation.reportError("Error instantiating navX MXP: " + ex.getMessage(), true);
      }

      navX.reset();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("NavX Yaw Angle", this.getYaw());
    SmartDashboard.putNumber("NavX Roll", this.getRoll());
    SmartDashboard.putNumber("NavX Pitch", this.getPitch());
  }


  public float getPitch(){
    //Returns the current pitch value (in degrees, from -180 to 180) reported by the sensor. 
    //Pitch is a measure of rotation around the Y Axis. The line that runs through the center of the robot
    //from right to left, X (Pitch)	Left/Right	– Left / + Right	+ Tilt Backwards
    return navX.getPitch();
  }

  public float getRoll(){
    //Returns the current pitch value (in degrees, from -180 to 180) reported by the sensor. 
    //Pitch is a measure of rotation around the X Axis. The line that runs through the center of the robot
    //from front to back

    //This is the measurement that will be used  to determine how to level the bar
    return navX.getRoll();
  }

  public float getYaw(){
    //Returns the current yaw value (in degrees, from -180 to 180) reported by the sensor. 
    //Yaw is a measure of rotation around the Z Axis (which is perpendicular to the earth)..
    return navX.getYaw();
  }

  public void reset(){
    navX.reset();
  }

}
