/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.command_groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.*;


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

    //Step 1 - Deploy front lift 
    addSequential(new FrontLiftDown());

    //Step 2 - Drive forward 
    addSequential(new DriveForward());

    //Step 3 - deploy rear lift 
    addSequential(new RearLiftDown());

    //step 4 - drive forward 
    addSequential(new DriveForward());

    //step 5 - return front lift + back lift to bot
    addParallel(new FrontLiftUp());
    addSequential(new RearLiftUp());
  }
}
