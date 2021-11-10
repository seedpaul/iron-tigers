/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;
import frc.robot.commands.auto.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class StraightBack extends SequentialCommandGroup {
  /**
   * Creates a new WeekZeroAuto.
   */
  
  public StraightBack(Bombardier in_Bombardier, DriveTrain in_drive) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand())
    super(new ShootPreloaded(in_Bombardier),
          new DriveStraight(in_drive, 36, 0.25));
  }
}
