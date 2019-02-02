/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.ElbowPositions;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;


/**
 * Add your docs here.
 */
public class IntakeElbow extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  //TODO: encoder for angle control

  private static TalonSRX intakeElbowSRX = new TalonSRX(RobotMap.SRXIntakeElbow);

  private static IntakeElbow instance;

  private IntakeElbow(){
    init();
  }

  private void init(){
    intakeElbowSRX.configFactoryDefault();

    intakeElbowSRX.set(ControlMode.PercentOutput,0);

    intakeElbowSRX.setNeutralMode(NeutralMode.Brake);

    //this is a very important line of code
    intakeElbowSRX.setSensorPhase(true);

    intakeElbowSRX.configForwardSoftLimitEnable(true);
    intakeElbowSRX.configReverseSoftLimitEnable(true);

    intakeElbowSRX.configForwardSoftLimitThreshold(ElbowPositions.getMax());
    intakeElbowSRX.configReverseSoftLimitThreshold(ElbowPositions.getMin());

    intakeElbowSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    
    intakeElbowSRX.configNominalOutputForward(0,30);
    intakeElbowSRX.configNominalOutputReverse(0,30);
    intakeElbowSRX.configPeakOutputForward(1, 30);
    intakeElbowSRX.configPeakOutputReverse(-1, 30);

    intakeElbowSRX.configAllowableClosedloopError(0, 0, 30);

		intakeElbowSRX.config_kF(0, 0.0, 30);
		intakeElbowSRX.config_kP(0, 0.15, 30);
		intakeElbowSRX.config_kI(0, 0.0, 30);
    intakeElbowSRX.config_kD(0, 1.0, 30);

    //pre-flight checklist to make sure lift is all the way @ bottom
    intakeElbowSRX.setSelectedSensorPosition(0,0,30);
  }

  public static IntakeElbow getInstance() {
    if(instance == null){
      instance = new IntakeElbow();
    }
    return instance;
  }

  public void up(){
    intakeElbowSRX.set(ControlMode.Position, ElbowPositions.up());
    intakeElbowSRX.getSelectedSensorPosition();
    System.out.println("Sensor:"+intakeElbowSRX.getSelectedSensorPosition());
  }

  public void down(){
    intakeElbowSRX.set(ControlMode.Position, ElbowPositions.down());
    intakeElbowSRX.getSelectedSensorPosition();
    System.out.println("Sensor:"+intakeElbowSRX.getSelectedSensorPosition());
  }

  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
