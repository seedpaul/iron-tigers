/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

  private static Talon elevatorTalon = new Talon(1);
  private static Elevator instance;

  private Elevator(){
    init();
  }

  private void init(){

  }

  public static Elevator getInstance() {
    if(instance == null){
      instance = new Elevator();
    }
    return instance;
  }

  public void up(){

    elevatorTalon.set(1.0);
  }

  public void down(){

    elevatorTalon.set(-1.0);
  }

  public void stop(){
    elevatorTalon.set(0.0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
