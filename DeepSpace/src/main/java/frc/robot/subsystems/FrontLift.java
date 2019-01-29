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
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class FrontLift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private final TalonSRX frontLiftRight = new TalonSRX(RobotMap.SRXFrontLiftRight);
  private final VictorSPX frontLiftLeft = new VictorSPX(RobotMap.SPXFrontLiftLeft);

  private static FrontLift instance;

  private FrontLift(){
    init();
  }

  private void init(){
    frontLiftRight.configFactoryDefault();
    frontLiftLeft.configFactoryDefault();

    frontLiftRight.set(ControlMode.PercentOutput,0);
    frontLiftLeft.set(ControlMode.Follower,0);

    frontLiftRight.setNeutralMode(NeutralMode.Brake);
    frontLiftLeft.setNeutralMode(NeutralMode.Brake);

    //this is a very important line of code
    frontLiftRight.setSensorPhase(true);

    frontLiftRight.configForwardSoftLimitEnable(true);
    frontLiftRight.configReverseSoftLimitEnable(true);

    frontLiftRight.configForwardSoftLimitThreshold(LiftPositions.getHighestPosition());
    frontLiftRight.configReverseSoftLimitThreshold(LiftPositions.getHomePosition());

    frontLiftRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    
    frontLiftRight.configNominalOutputForward(0,30);
    frontLiftRight.configNominalOutputReverse(0,30);
    frontLiftRight.configPeakOutputForward(1, 30);
    frontLiftRight.configPeakOutputReverse(-1, 30);

    frontLiftRight.configAllowableClosedloopError(0, 0, 30);

		frontLiftRight.config_kF(0, 0.0, 30);
		frontLiftRight.config_kP(0, 0.15, 30);
		frontLiftRight.config_kI(0, 0.0, 30);
    frontLiftRight.config_kD(0, 1.0, 30);

    frontLiftLeft.follow(frontLiftRight);
    //pre-flight checklist to make sure lift is all the way @ bottom
    frontLiftRight.setSelectedSensorPosition(0,0,30);
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
