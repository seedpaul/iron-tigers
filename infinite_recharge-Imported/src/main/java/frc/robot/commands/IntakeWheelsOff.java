/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeWheels;

public class IntakeWheelsOff extends CommandBase {

  private final IntakeWheels m_IntakeWheels;
  /**
   * Creates a new IntakeDown.
   */
  public IntakeWheelsOff(IntakeWheels in_IntakeWheels) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_IntakeWheels = in_IntakeWheels;
    addRequirements(m_IntakeWheels);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_IntakeWheels.off();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
