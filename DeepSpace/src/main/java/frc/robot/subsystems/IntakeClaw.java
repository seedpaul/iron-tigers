/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class IntakeClaw extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static TalonSRX intakeClaw = new TalonSRX(RobotMap.TalonIntakeClaw);
  private static Servo flipper1Servo = new Servo(0);
  private static Servo flipper2Servo = new Servo(1);
  private static Servo flipper3Servo = new Servo(2);

  private static IntakeClaw instance;

  private IntakeClaw(){
    init();
  }

  private void init(){
    flipper1Servo.setSpeed(1.0);
    flipper2Servo.setSpeed(1.0);
    flipper3Servo.setSpeed(1.0);
  }

  public static IntakeClaw getInstance(){
    if (instance==null){
      instance = new IntakeClaw();
    }
    return instance;
  }

  public void open(){}
  public void close(){}

  public void extendFlippers(){
    flipper1Servo.set(0.0);
    flipper2Servo.set(0.0);
    flipper3Servo.set(1.0);
  }
  public void retractFlippers(){
    flipper1Servo.set(1.0);
    flipper2Servo.set(1.0);
    flipper3Servo.set(0.0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
