/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.*;

public abstract class CommandBase extends Command {
    
  public static DriveTrain driveTrain;
  public static Elevator elevator;
  public static HatchPanelFlipper panelFlipper;
  public static FrontLift frontLift;
  public static IntakeWheels intakeWheels;
  public static IntakeElbow intakeElbow;
  public static RearLift rearLift;
  public static Camera camera;
  
  public static OI oi;

  //initializes all of static variables
  public static void init() {

    driveTrain  = DriveTrain.getInstance();
    elevator = Elevator.getInstance();
    frontLift = FrontLift.getInstance();
    panelFlipper = HatchPanelFlipper.getInstance();
	  camera = Camera.getInstance();
    intakeWheels= IntakeWheels.getInstance();
    intakeElbow = IntakeElbow.getInstance();
    rearLift = RearLift.getInstance();

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
