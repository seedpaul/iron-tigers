package frc.robot.command_groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.elevator.*;
import frc.robot.commands.front_lift.*;
import frc.robot.commands.rear_lift.*;
import frc.robot.commands.drivetrain.*;

public class ClimbLevel6 extends CommandGroup {

  public ClimbLevel6() {

    //Step 1 - Elevator all the way down
    //addSequential(new ElevatorHome());

    //Step 2 - Deploy front lift 
    addSequential(new FrontLiftHab6(),2);

    //Step 3 - Deploy rear lift and start 
    //  front lift omn it way down
    //addParallel(new DriveStraight(5, 0.5));

    addParallel(new RearLiftHab6(),2);

    addParallel(new FrontLiftClimbStep1(),2);

    addSequential(new FrontLiftClimbStep2(),2);

    addSequential(new DriveStraight(20.0, 0.5)); // until about ITS OVER 9000 DUBBAH BRAP BRAP BRAP 

    //Step 5 - deploy rear lift 
    // addSequential(new RearLiftHome());

    //step 6 - drive forward 
    // addParallel(new DriveStraight(0,0));
    // addParallel(new FrontLiftHome());
  }
}
