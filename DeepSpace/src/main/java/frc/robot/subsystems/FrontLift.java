/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class FrontLift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  //TODO:we need an encoder to control height
  //need talonSRX

  private final WPI_TalonSRX frontLiftRight = new WPI_TalonSRX(RobotMap.SRXFrontLiftRight);
  private final WPI_TalonSRX frontLiftLeft = new WPI_TalonSRX(RobotMap.SRXFrontLiftLeft);

  private static FrontLift instance;

  private FrontLift(){
    init();
  }

  private void init(){

  }

  public static FrontLift getInstance(){
    if(instance == null){
      instance = new FrontLift();
    }
    return instance;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
