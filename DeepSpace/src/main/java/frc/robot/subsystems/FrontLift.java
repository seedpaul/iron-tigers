/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.RobotMap;
import frc.robot.FrontLiftPositions;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class FrontLift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private final TalonSRX liftTalonSRX = new TalonSRX(RobotMap.SRXFrontLift);
  private final VictorSPX liftVictorSPX = new VictorSPX(RobotMap.SPXFrontLift);

  private int currentPosition = 0;

  private static FrontLift instance;

  private FrontLift(){
    init();
  }

  private void init(){
    liftTalonSRX.configFactoryDefault();
    liftVictorSPX.configFactoryDefault();

    liftTalonSRX.set(ControlMode.PercentOutput,0);
    liftVictorSPX.set(ControlMode.Follower,0);

    liftTalonSRX.setNeutralMode(NeutralMode.Brake);
    liftVictorSPX.setNeutralMode(NeutralMode.Brake);

    //this is a very important line of code
    liftTalonSRX.setSensorPhase(false);

    liftTalonSRX.configForwardSoftLimitEnable(true);
    liftTalonSRX.configReverseSoftLimitEnable(true);

    liftTalonSRX.configForwardSoftLimitThreshold(FrontLiftPositions.getHighestPosition());
    liftTalonSRX.configReverseSoftLimitThreshold(FrontLiftPositions.getHomePosition());

    liftTalonSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    
    liftTalonSRX.configNominalOutputForward(0,30);
    liftTalonSRX.configNominalOutputReverse(0,30);
    liftTalonSRX.configPeakOutputForward(1, 30);
    liftTalonSRX.configPeakOutputReverse(-1, 30);

    liftTalonSRX.configAllowableClosedloopError(0, 0, 30);

		liftTalonSRX.config_kF(0, 0.0, 30);
		liftTalonSRX.config_kP(0, 0.15, 30);
		liftTalonSRX.config_kI(0, 0.0, 30);
    liftTalonSRX.config_kD(0, 1.0, 30);

    liftVictorSPX.follow(liftTalonSRX);
    //pre-flight checklist to make sure lift is all the way @ bottom
    liftTalonSRX.setSelectedSensorPosition(0,0,30);
  }

  public static FrontLift getInstance(){
    if(instance == null){
      instance = new FrontLift();
    }
    return instance;
  }

  public void gotoToLevel6position(){
    setPosition(FrontLiftPositions.habLevel6);
  }

  public void goToLevel9Position(){
    setPosition(FrontLiftPositions.habLevel19);
  }

  public void goToHome(){
    setPosition(FrontLiftPositions.home);
  }

  private void setPosition(int position){

    //counterclockwise is up, 
    liftTalonSRX.set(ControlMode.Position, FrontLiftPositions.Position[position]);
    liftTalonSRX.getSelectedSensorPosition();
    //System.out.println("Sensor:"+liftTalonSRX.getSelectedSensorPosition());
    // System.out.println("INDEX:"+position);
    System.out.println("Target VALUE:"+ FrontLiftPositions.Position[position] + "\n");
  }

  public int getSensorValue(){
    return liftTalonSRX.getSelectedSensorPosition();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
