/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//harris wuz here

package frc.robot.subsystems;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


/**
 * Add your docs here.
 */
public class IntakeWheels extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static final VictorSPX intakeWheels = new VictorSPX(RobotMap.VictorIntakeWheels);
  private static final TalonSRX intakeflipper = new TalonSRX(RobotMap.VictorIntakeFlipper);
  private static final DigitalInput boschMotorDigitalInput = new DigitalInput(RobotMap.flipperEncoder);
  private static final Counter motorCounter = new Counter(boschMotorDigitalInput);
  private static int position = 150000;
  private static Boolean opening = true;

  // 174.9 ping per revolution
  private static final int openPosition = 2000000;
  private static final int closePosition = 10000;

  private static IntakeWheels instance;

  private IntakeWheels(){
    init();
  }

  private void init(){
    intakeWheels.configPeakOutputForward(1);
    intakeWheels.configPeakOutputReverse(1);
    motorCounter.reset();
  }

  public void periodic() {

    // System.out.println("current: " + position);
    // System.out.println("motorCounter.get(): " + motorCounter.get() + "\n");
    // System.out.println("intakeflipper.getMotorOutputVoltage(): " + intakeflipper.getMotorOutputVoltage());

    if(Math.abs(intakeflipper.getMotorOutputVoltage()) > 10.0){
      
      System.out.println("were applying voltage to the motor. " + Math.abs(intakeflipper.getMotorOutputVoltage())+ "volts");
      if (opening) {
        position += motorCounter.get();
      } else {
        position -= motorCounter.get();
      }
      // reset the counter to zero
      motorCounter.reset();
    }
  }

  public static IntakeWheels getInstance(){
    if (instance==null){
      instance = new IntakeWheels();
    }
    return instance;

  }

  public boolean openFlipper(){
    System.out.println("openFlipper");
    opening = true;
    intakeflipper.set(ControlMode.PercentOutput, 1.0);
    return position <= openPosition ;
  }

  public boolean closeFlipper(){
    System.out.println("closeFlipper");
    opening = false;
    intakeflipper.set(ControlMode.PercentOutput, -1.0);
    return position >= closePosition;
  }

  public void stopFlipper(){
    intakeflipper.set(ControlMode.PercentOutput, 0.0);
  }

  public void injest(){

  }

  public void eject(){

  }
//TODO:function wheels hold -- we may be able to automatically do with by monitoring the voltage


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
