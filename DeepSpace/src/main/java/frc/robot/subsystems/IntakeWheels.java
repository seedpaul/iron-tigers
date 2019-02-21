/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeWheels extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static final TalonSRX intakeWheels = new TalonSRX(RobotMap.TalonIntakeWheels);
  private static final TalonSRX intakeflipper = new TalonSRX(RobotMap.TalonIntakeFlipper);
  private final Encoder flipperEncoder = new Encoder(RobotMap.flipperEncoderChannelA, RobotMap.flipperEncoderChannelB, false, Encoder.EncodingType.k4X);

  private static final double flipper_home = 0.0;
  private static final double flipper_open = 520.0;

  //pulse per rev 2048
  private static IntakeWheels instance;

  private IntakeWheels(){
    init();
  }

  private void init(){

    intakeWheels.configFactoryDefault();
    intakeflipper.configFactoryDefault();

    intakeWheels.set(ControlMode.PercentOutput,0);
    intakeWheels.setNeutralMode(NeutralMode.Brake);

    intakeflipper.set(ControlMode.PercentOutput,0);
    intakeflipper.setNeutralMode(NeutralMode.Brake);
    
    intakeWheels.configNominalOutputForward(0,30);
    intakeWheels.configNominalOutputReverse(0,30);
    intakeWheels.configPeakOutputForward(1, 30);
    intakeWheels.configPeakOutputReverse(-1, 30);

    intakeflipper.configNominalOutputForward(0,30);
    intakeflipper.configNominalOutputReverse(0,30);
    intakeflipper.configPeakOutputForward(1, 30);
    intakeflipper.configPeakOutputReverse(-1, 30);

    intakeWheels.configPeakCurrentLimit(35, 30);
    intakeWheels.configPeakCurrentDuration(120, 30);
    intakeWheels.configContinuousCurrentLimit(25, 30);
    intakeWheels.enableCurrentLimit(true);

    intakeflipper.configPeakCurrentLimit(35, 30);
    intakeflipper.configPeakCurrentDuration(120, 30);
    intakeflipper.configContinuousCurrentLimit(25, 30);
    intakeflipper.enableCurrentLimit(true);

    flipperEncoder.reset();
    addInfoToDashboard();

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

    boolean done = false;
    double currentDistance = flipperEncoder.getDistance(); 

    if(currentDistance < flipper_open){
      intakeflipper.set(ControlMode.PercentOutput, 1.0);
    }
    else{
      done = true;
      //tewst
    }
    
    addInfoToDashboard();
    return done;
  }

  public boolean closeFlipper(){

    boolean done = false;
    double currentDistance = flipperEncoder.getDistance(); 

    if(currentDistance > flipper_home){
      intakeflipper.set(ControlMode.PercentOutput, -1.0);
    }
    else{
      done = true;
    }
    
    addInfoToDashboard();
    return done;
  }


  public void simpleCloseFlipper(){

    intakeflipper.set(ControlMode.PercentOutput, -1.0);
  }

  public void stopFlipper(){
    intakeflipper.set(ControlMode.PercentOutput, 0.0);
    addInfoToDashboard();
  }

  public void injest(){
    intakeWheels.set(ControlMode.PercentOutput, -0.5);
  }

  public void eject(){
    intakeWheels.set(ControlMode.PercentOutput, 1.0);
  }

  public void stop(){
    intakeWheels.set(ControlMode.PercentOutput, 0.0);
  }


  private void addInfoToDashboard(){
    SmartDashboard.putData("flipperEncoder", flipperEncoder);


  }
//TODO:function wheels hold -- we may be able to automatically do with by monitoring the voltage


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
