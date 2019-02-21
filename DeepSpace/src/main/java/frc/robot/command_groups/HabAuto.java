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
    // addParallel(new Command1());

    //Step 1 - Deploy front lift 
    addSequential(new FrontLiftClimbStep1());

    //Step 2 - Drive forward 
    addSequential(new DriveStraight(0,0));

    //Step 3 - deploy rear lift 
    addSequential(new RearLiftHab6());

    //step 4 - drive forward 
    addSequential(new DriveStraight(0,0));

    //step 5 - return front lift + back lift to bot
    addParallel(new FrontLiftHome());
    addSequential(new RearLiftHome());
  }
}
