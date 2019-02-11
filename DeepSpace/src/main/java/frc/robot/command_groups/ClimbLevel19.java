/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.command_groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.*;

public class ClimbLevel19 extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ClimbLevel19() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.

    //Step 1 - deploy front lift to level 9
    addSequential(new FrontLiftDown());

    //Step 2 - front and rear lift down (parallel)
    addParallel(new FrontLiftDown());
    addSequential(new RearLiftDown());

    //Step 3 - drive forward 
    addSequential(new DriveForward());

    //Step 4 - front lift up into bot
    addSequential(new FrontLiftUp());

    //Step 5 - drive forward
    addSequential(new DriveForward());

    //Step 6 - rear lift up into bot
    addSequential(new RearLiftUp());

    //Step 7 - drive forward
    addSequential(new DriveForward());
  }
}
