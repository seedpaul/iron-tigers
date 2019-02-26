/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


public class ClawFlipperOpen extends CommandBase {

  private static boolean complete = false;
  
  public ClawFlipperOpen() {
    // Use requires() here to declare subsystem dependencies
    requires(intakeClaw);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    complete = intakeClaw.flipperExtend();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return complete;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    intakeClaw.flipperStop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    intakeClaw.flipperStop();
  }
}
