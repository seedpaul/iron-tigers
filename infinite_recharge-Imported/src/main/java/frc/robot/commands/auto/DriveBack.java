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

public class DriveBack extends CommandBase {

  private final DriveTrain m_Drive;
  private double myDistance;
	private double targetSpeed;
	private double currentSpeed;
	private double leftEncoderDistance;
	private double rightEncoderDistance;
	private boolean complete = false;
	private static double Kp = 1;
    /**
   * Creates a new DriveStraight.
   */
  public DriveBack(DriveTrain in_drive, double distance, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Drive = in_drive;
    addRequirements(m_Drive);
    myDistance = distance;
    targetSpeed = -speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    	currentSpeed = -0.1;
    	leftEncoderDistance = 0.0;
    	rightEncoderDistance = 0.0;
    	m_Drive.resetLeftEncoder();
    	m_Drive.resetRightEncoder();
    	m_Drive.resetGyro();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(currentSpeed > targetSpeed) {
      currentSpeed -= 0.095;
    }
    leftEncoderDistance = m_Drive.getLeftEncoderDistance();
    rightEncoderDistance = m_Drive.getRightEncoderDistance();
    
    if (leftEncoderDistance >= myDistance && rightEncoderDistance >= myDistance) {
      double angle = m_Drive.getGyroAngleConverted(); // get current heading
      m_Drive.autoDrive(currentSpeed, (angle*Kp), false); // drive towards heading 0
      Timer.delay(0.04); 
    } else {
      complete = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    leftEncoderDistance = 0.0;
    rightEncoderDistance = 0.0;
    m_Drive.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return complete;
  }
}
