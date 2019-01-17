/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.*;

public abstract class CommandBase extends Command {
  public static DriveTrain driveTrain;

  public static OI oi;

  //initializes all of static variables
  public static void init() {

      driveTrain  = DriveTrain.getInstance();
      
      oi = new OI();
      oi.init();
  }
  

  //Constructor
  public CommandBase(String name) {
    
      super(name);
  }

  //Default Constructor
  public CommandBase() {
      super();
  }
  
  public CommandBase(Double timeout){
    super(timeout);
  }
}
