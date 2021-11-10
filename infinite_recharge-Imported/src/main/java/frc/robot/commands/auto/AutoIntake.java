/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeElbow;
import frc.robot.subsystems.IntakeWheels;

public class AutoIntake extends CommandBase {
  private final IntakeElbow m_IntakeElbow;
  private final IntakeWheels m_IntakeWheels;
  private boolean done = false;
  /**
   * Creates a new AutoIntake.
   */
  public AutoIntake(IntakeElbow in_IntakeElbow, IntakeWheels in_IntakeWheels) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_IntakeWheels = in_IntakeWheels;
    m_IntakeElbow = in_IntakeElbow;
    addRequirements(m_IntakeElbow);
    addRequirements(m_IntakeWheels);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_IntakeWheels.on();
    done = m_IntakeElbow.down();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
