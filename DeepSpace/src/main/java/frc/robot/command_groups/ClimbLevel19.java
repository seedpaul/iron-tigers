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
    // addParallel(new Command1());

    //Step 1 - Elevator all the way down
   
    //addSequential(new ElevatorHome());

    //Step 2 - Deploy front lift 
    System.out.println("FrontLiftHab19");
    addSequential(new FrontLiftHab19(),2);

    //Step 3 - Deploy rear lift and start 
    //  front lift omn it way down
    //addParallel(new DriveStraight(5, 0.5));

    System.out.println("RearLiftHab19");
    addParallel(new RearLiftHab19(),2);
    System.out.println("FrontLiftClimbStep1");
    addParallel(new FrontLiftClimbStep1(),2);

    System.out.println("FrontLiftClimbStep2");
    addSequential(new FrontLiftClimbStep2(),2);

    //Step 4 - Drive forward 
    System.out.println("DriveStraight");
    addSequential(new DriveStraight(20.0, 0.5)); // until about ITS OVER 9000 DUBBAH BRAP BRAP BRAP 

    //Step 5 - deploy rear lift 
    // addSequential(new RearLiftHome());

    // //step 6 - drive forward 
    // addParallel(new DriveForward());
    // addParallel(new FrontLiftHome());
  }
}
