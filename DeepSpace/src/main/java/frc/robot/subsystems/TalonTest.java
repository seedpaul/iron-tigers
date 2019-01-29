/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.ElevatorPositions;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class TalonTest extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private final TalonSRX testSRX5 = new TalonSRX(5);
  //private final TalonSRX testSRX6 = new TalonSRX(6);
  private final VictorSPX testSPX7 = new VictorSPX(7);

  private int currentPosition = 0;

  private static TalonTest instance;

  private TalonTest(){
    init();
  }

  public static TalonTest getInstance(){
    if (instance == null){
      instance = new TalonTest();
    }

    return instance;
  }

  private void init(){

    testSRX5.configFactoryDefault();
    testSPX7.configFactoryDefault();

    testSRX5.set(ControlMode.PercentOutput,0);
    testSPX7.set(ControlMode.Follower,0);

    testSRX5.setNeutralMode(NeutralMode.Brake);
    testSPX7.setNeutralMode(NeutralMode.Brake);

    //this is a very important line of code
    testSRX5.setSensorPhase(true);

    testSRX5.configForwardSoftLimitEnable(true);
    testSRX5.configReverseSoftLimitEnable(true);

    testSRX5.configForwardSoftLimitThreshold(ElevatorPositions.getHighestPosition());
    testSRX5.configReverseSoftLimitThreshold(ElevatorPositions.getHomePosition());

    testSRX5.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    
    testSRX5.configNominalOutputForward(0,30);
    testSRX5.configNominalOutputReverse(0,30);
    testSRX5.configPeakOutputForward(1, 30);
    testSRX5.configPeakOutputReverse(-1, 30);

    testSRX5.configPeakCurrentLimit(3, 30);
		testSRX5.configPeakCurrentDuration(5, 30);
		testSRX5.configContinuousCurrentLimit(1, 30);
		testSRX5.enableCurrentLimit(false); // Honor initial setting

    testSRX5.configAllowableClosedloopError(0, 0, 30);

		testSRX5.config_kF(0, 0.0, 30);
		testSRX5.config_kP(0, 0.15, 30);
		testSRX5.config_kI(0, 0.0, 30);
    testSRX5.config_kD(0, 1.0, 30);

    // int absolutePosition = testSRX5.getSensorCollection().getPulseWidthPosition();
    // absolutePosition &= 0xFFF;
    //testSRX5.setSelectedSensorPosition(absolutePosition,0,30);

    testSPX7.follow(testSRX5);
    testSRX5.setSelectedSensorPosition(0,0,30);

  }

  public void up(){

    if(currentPosition < (ElevatorPositions.Position.length-1)){
      currentPosition++;
      setPosition(currentPosition);
      System.out.println("current:" + testSRX5.getOutputCurrent());
    }

  }

  public void down(){

    if(currentPosition > 0){
      currentPosition--;
      setPosition(currentPosition);
      System.out.println("current:" + testSRX5.getOutputCurrent());
    }
  }

  private void setPosition(int position){

    //counterclockwise is up, 
    testSRX5.set(ControlMode.Position, ElevatorPositions.Position[position]);
    testSRX5.getSelectedSensorPosition();
    System.out.println("Sensor:"+testSRX5.getSelectedSensorPosition());
    System.out.println("INDEX:"+position);
    System.out.println("VALUE:"+ ElevatorPositions.Position[position] + "\n");
  }

  public void limitCurrent(){
    System.out.println("limitCurrent");
    System.out.println("current:" + testSRX5.getOutputCurrent());
    testSRX5.enableCurrentLimit(true);
  }

  public void unlimitCurrent(){
    System.out.println("unlimitCurrent");
    System.out.println("current:" + testSRX5.getOutputCurrent());
    testSRX5.enableCurrentLimit(false);
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

}
