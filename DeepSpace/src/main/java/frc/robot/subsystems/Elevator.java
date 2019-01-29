/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import frc.robot.RobotMap;
import frc.robot.ElevatorPositions;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static TalonSRX elevatorSRX = new TalonSRX(RobotMap.SRXElevator);
  private static VictorSPX elevatorSPX = new VictorSPX(RobotMap.SPXElevator);

  private int currentPosition = 0;

  private static Elevator instance;

  private Elevator(){
    init();
  }

  private void init(){
    elevatorSRX.configFactoryDefault();
    elevatorSPX.configFactoryDefault();

    elevatorSRX.set(ControlMode.PercentOutput,0);
    elevatorSPX.set(ControlMode.Follower,0);

    elevatorSRX.setNeutralMode(NeutralMode.Brake);
    elevatorSPX.setNeutralMode(NeutralMode.Brake);

    //this is a very important line of code
    elevatorSRX.setSensorPhase(true);

    elevatorSRX.configForwardSoftLimitEnable(true);
    elevatorSRX.configReverseSoftLimitEnable(true);

    elevatorSRX.configForwardSoftLimitThreshold(ElevatorPositions.getHighestPosition());
    elevatorSRX.configReverseSoftLimitThreshold(ElevatorPositions.getHomePosition());

    elevatorSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    
    elevatorSRX.configNominalOutputForward(0,30);
    elevatorSRX.configNominalOutputReverse(0,30);
    elevatorSRX.configPeakOutputForward(1, 30);
    elevatorSRX.configPeakOutputReverse(-1, 30);

    elevatorSRX.configAllowableClosedloopError(0, 0, 30);

		elevatorSRX.config_kF(0, 0.0, 30);
		elevatorSRX.config_kP(0, 0.15, 30);
		elevatorSRX.config_kI(0, 0.0, 30);
    elevatorSRX.config_kD(0, 1.0, 30);

    elevatorSPX.follow(elevatorSRX);
    //pre-flight checklist to make sure lift is all the way @ bottom
    elevatorSRX.setSelectedSensorPosition(0,0,30);
  }

  public static Elevator getInstance() {
    if(instance == null){
      instance = new Elevator();
    }
    return instance;
  }

  public void up(){

    if(currentPosition < (ElevatorPositions.Position.length-1)){
      currentPosition++;
      setPosition(currentPosition);
      System.out.println("current:" + elevatorSRX.getOutputCurrent());
    }

  }

  public void down(){

    if(currentPosition > 0){
      currentPosition--;
      setPosition(currentPosition);
      System.out.println("current:" + elevatorSRX.getOutputCurrent());
    }
  }

  private void setPosition(int position){

    //counterclockwise is up, 
    elevatorSRX.set(ControlMode.Position, ElevatorPositions.Position[position]);
    elevatorSRX.getSelectedSensorPosition();
    System.out.println("Sensor:"+elevatorSRX.getSelectedSensorPosition());
    System.out.println("INDEX:"+position);
    System.out.println("VALUE:"+ ElevatorPositions.Position[position] + "\n");
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
