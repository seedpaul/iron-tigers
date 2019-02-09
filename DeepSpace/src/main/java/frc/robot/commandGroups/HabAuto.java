/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.LiftDown;

public class HabAuto extends CommandGroup {
  /**
   * Add your docs here.
   */
  public HabAuto() {
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

    //TODO: make FrontLiftDown command
    //Step 1 - Deploy front lift (sequential)

    //TODO: Drive forward command
    //Step 2 - Drive forward (sequential)

    //TODO: make RearLiftDown command
    //Step 3 - deploy rear lift (sequential)

    //step 4 - drive forward (sequential)

    //TODO: make RearLiftUp ad FrontLiftUp commands
    //step 5 - return front lift + back lift (parallel)
  }
}
