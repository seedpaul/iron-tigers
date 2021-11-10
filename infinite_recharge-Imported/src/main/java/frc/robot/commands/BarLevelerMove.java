/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.BarLeveler;

public class BarLevelerMove extends CommandBase {
  private final BarLeveler m_BarLeveler;
  private final XboxController m_driver;
  /**
   * Creates a new BarLevelerLeft.
   */
  public BarLevelerMove(BarLeveler in_BarLeveler, XboxController in_driver) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_BarLeveler = in_BarLeveler;
    m_driver = in_driver;
    addRequirements(m_BarLeveler);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double right_input = m_driver.getRawAxis(RobotMap.rightTrigger);
    double left_input = m_driver.getRawAxis(RobotMap.leftTrigger);
    m_BarLeveler.move(left_input, right_input);
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
