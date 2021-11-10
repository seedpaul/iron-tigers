/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class TurnToAngle extends CommandBase {
  private final DriveTrain m_Drive;
  private double m_angle;
  private boolean complete = false;
  private double m_speed;

  /**
   * Creates a new TurnToAngle.
   */
  public TurnToAngle(DriveTrain in_drive, double in_angle, double in_speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Drive = in_drive;
    addRequirements(m_Drive);
    m_angle = in_angle;
    m_speed = in_speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double measuredAngle = m_Drive.getGyroAngle();

    // m_Drive.getGyroAngleConverted(); // get current heading
    // m_Drive.autoDrive(0, -(angle*Kp), true); // drive towards heading 0

    	if(m_angle >= 0) {
    		turnClockWise(measuredAngle);
    	}
    	else {
    		turnCounterClockWise(measuredAngle);
    	}
  }

  private void turnCounterClockWise(double measuredAngle) {
    // turn left
    if(measuredAngle > m_angle){
      m_Drive.tankDrive(-m_speed,m_speed);
      Timer.delay(0.01);
    }
    else {
      complete = true;
    }
  }
  
  private void turnClockWise(double measuredAngle) {
    //turn right
    if(measuredAngle < m_angle){
      m_Drive.tankDrive(m_speed,-m_speed);
      Timer.delay(0.01);
    }
    else {
      complete = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Drive.stop();
    m_Drive.resetGyro();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return complete;
  }
}
