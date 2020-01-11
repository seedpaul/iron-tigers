/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.RobotMap;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */

   //declare the motor crontrollers
   private final WPI_VictorSPX frontRightVictor = new WPI_VictorSPX(RobotMap.VictorFrontRight);
   private final WPI_VictorSPX frontLeftVictor = new WPI_VictorSPX(RobotMap.VictorFrontLeft);
   private final WPI_TalonSRX backRightTalon = new WPI_TalonSRX(RobotMap.TalonBackRight);
   private final WPI_TalonSRX backLeftTalon = new WPI_TalonSRX(RobotMap.TalonBackLeft);
  

   //groups the motor controllers into left and right groups
   private final SpeedControllerGroup rightSCG = new SpeedControllerGroup(frontRightVictor, backRightTalon);
   private final SpeedControllerGroup leftSCG = new SpeedControllerGroup(frontLeftVictor, backLeftTalon);

   //declares the drive train (which consists of each motor controller)
   private final DifferentialDrive robotDrive = new DifferentialDrive(leftSCG, rightSCG);


   private static DriveTrain instance;

  //  private DriveTrain(){
  //   init();
  //  }


   public static DriveTrain getInstance(){
     if (instance == null){
       instance = new DriveTrain();
     }

     return instance;
   }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
