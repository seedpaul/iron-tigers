/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class IntakeWheels extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static final VictorSPX intakeWheels = new VictorSPX(RobotMap.VictorIntakeWheels);
  private static final TalonSRX intakeflipper = new TalonSRX(RobotMap.VictorIntakeFlipper);
  private static final DigitalInput boschMotorDigitalInput = new DigitalInput(RobotMap.flipperEncoder);
  private static final Counter motorCounter = new Counter(boschMotorDigitalInput);
  
  private static int position = 150000;
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
  }

  public static IntakeWheels getInstance(){
    if (instance==null){
      instance = new IntakeWheels();
    }
    return instance;

  }

  public boolean openFlipper(){
    intakeflipper.set(ControlMode.PercentOutput, 1.0);
    return true;
  }

  public boolean closeFlipper(){
    intakeflipper.set(ControlMode.PercentOutput, -1.0);
    return true;
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
