package frc.robot.command_groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.front_lift.*;
import frc.robot.commands.rear_lift.*;
import frc.robot.commands.drivetrain.*;

public class HabAuto extends CommandGroup {
  /**
   * Add your docs here.
   */
  public HabAuto() {

    //Step 1 - Deploy front lift 
    addSequential(new FrontLiftClimbStep1());

    //Step 2 - Drive forward 
    //addSequential(new DriveStraight(0,0));

    //Step 3 - deploy rear lift 
    addSequential(new RearLiftHab6());

    //step 4 - drive forward 
   //addSequential(new DriveStraight(0,0));

    //step 5 - return front lift + back lift to bot
    addParallel(new FrontLiftHome());
    addSequential(new RearLiftHome());
  }
}
