
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import frc.robot.RobotMap;
import frc.robot.IntakeClawPositions;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class IntakeClaw extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static TalonSRX intakeClaw = new TalonSRX(RobotMap.TalonIntakeWheels);
  private static Servo flipper1Servo = new Servo(0);
  private static Servo flipper2Servo = new Servo(1);
  private static Servo flipper3Servo = new Servo(2);
//hi
  private static IntakeClaw instance;

  private IntakeClaw(){
    init();
  }

  private void init(){
    flipper1Servo.setSpeed(1.0);
    flipper2Servo.setSpeed(1.0);
    flipper3Servo.setSpeed(1.0);    
    
    intakeClaw.set(ControlMode.PercentOutput,0);
    intakeClaw.configFactoryDefault();
    intakeClaw.setNeutralMode(NeutralMode.Brake);

    //this is a very important line of code in order to make foward on motor line up with foward on encoder
    intakeClaw.setSensorPhase(true);

    intakeClaw.configForwardSoftLimitEnable(true);
    intakeClaw.configReverseSoftLimitEnable(true);

    // tightest to widest
    //load/release hatch panel
    //hold hatch panel
    //clamp ball
    //ball release position
    //ball pick up

    intakeClaw.configForwardSoftLimitThreshold(IntakeClawPositions.getMax());
    intakeClaw.configReverseSoftLimitThreshold(IntakeClawPositions.getMin());

    intakeClaw.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    
    intakeClaw.configNominalOutputForward(0,30);
    intakeClaw.configNominalOutputReverse(0,30);
    intakeClaw.configPeakOutputForward(1, 30);
    intakeClaw.configPeakOutputReverse(-1, 30);

    // intakeClaw.configPeakCurrentLimit(3, 30);
    // intakeClaw.configPeakCurrentDuration(5, 30);
    // intakeClaw.configContinuousCurrentLimit(1, 30);
    // intakeClaw.enableCurrentLimit(false); // Honor initial setting

  }

  public static IntakeClaw getInstance(){
    if (instance==null){
      instance = new IntakeClaw();
    }
    return instance;
  }

  public void loadHatchPanel(){
    intakeClaw.set(ControlMode.Position, IntakeClawPositions.loadHatchPanel());
  }
  public void holdHatchPanel(){
    intakeClaw.set(ControlMode.Position, IntakeClawPositions.holdHatchPanel());
  }
  
  public void clampBall(){
    intakeClaw.set(ControlMode.Position, IntakeClawPositions.clampBall());
  }
  public void releaseBall(){
    intakeClaw.set(ControlMode.Position, IntakeClawPositions.releaseBall());
    extendFlippers();
  }
  public void loadBall(){
    intakeClaw.set(ControlMode.Position, IntakeClawPositions.loadBall());
  }


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
