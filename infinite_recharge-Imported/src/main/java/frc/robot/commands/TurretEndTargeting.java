/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Bombardier;

public class TurretEndTargeting extends CommandBase {

  private final Bombardier m_Bombardier;

  public TurretEndTargeting(Bombardier in_Bombardier) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Bombardier = in_Bombardier;
    addRequirements(m_Bombardier);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Bombardier.target(false, true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Bombardier.target(false, true);
    m_Bombardier.resetIndexer();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
