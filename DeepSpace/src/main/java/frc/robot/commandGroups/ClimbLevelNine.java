/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbLevelNine extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ClimbLevelNine() {
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
    //Step 2 - front and rear lift down (parallel)
    //Step 3 - drive forward 
    //Step 4 - front lift up into bot
    //Step 5 - drive forward
    //Step 6 - rear lift up into bot
    //Step 7 - drive forward
  }
}
