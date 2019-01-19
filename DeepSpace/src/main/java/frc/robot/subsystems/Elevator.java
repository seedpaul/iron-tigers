/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static WPI_VictorSPX elevatorRight = new WPI_VictorSPX(RobotMap.SPXElevatorRight);
  private static WPI_VictorSPX elevatorLeft = new WPI_VictorSPX(RobotMap.SPXElevatorLeft);

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

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
