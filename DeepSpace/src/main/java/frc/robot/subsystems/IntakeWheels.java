/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//harris wuz here

package frc.robot.subsystems;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


/**
 * Add your docs here.
 */
public class IntakeWheels extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static VictorSPX intakeWheels = new VictorSPX(RobotMap.VictorIntakeWheels);
  private static VictorSPX intakeflipper = new VictorSPX(RobotMap.VictorIntakeFlipper);
  private static Encoder flipper = new Encoder(RobotMap.flipperEncoderChannelA,RobotMap.flipperEncoderChannelB);

  private static IntakeWheels instance;

  private IntakeWheels(){
    init();
  }

  private void init(){
    intakeWheels.configPeakOutputForward(1);
    intakeWheels.configPeakOutputReverse(1);
  }

  public static IntakeWheels getInstance(){
    if (instance==null){
      instance = new IntakeWheels();
    }
    return instance;

  }

//TODO:function open flipper
//TODO:function close flipper
//TODO:function wheels intake
//TODO:function wheels output
//TODO:function wheels hold -- we may be able to automatically do with by monitoring the voltage


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
