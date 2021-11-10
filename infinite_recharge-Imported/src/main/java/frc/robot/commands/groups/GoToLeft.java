// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;
import frc.robot.commands.auto.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class GoToLeft extends SequentialCommandGroup {
  /** Creates a new ReverseTest. */
  public GoToLeft(Bombardier in_Bombardier, DriveTrain in_drive, IntakeWheels in_IntakeWheels, IntakeElbow in_Elbow) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    super(
          new ShootPreloaded(in_Bombardier),
          new TurnToAngle(in_drive, 35, 0.35), 
          new AutoIntake(in_Elbow, in_IntakeWheels),
          new DriveStraight(in_drive, 72, 0.25),
          // new TurnToAngle(in_drive, 35, 0.35),
          new Shoot(in_Bombardier)                             
          );
  }
}
