/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.drivetrain.ArcadeDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem{

  private final Talon frontRight = new Talon(2);
  private final Talon frontLeft = new Talon(3);
  private final Talon backRight = new Talon(4);
  private final Talon backLeft = new Talon(5);

  private final SpeedControllerGroup rightSCG = new SpeedControllerGroup(frontRight, backRight);
  private final SpeedControllerGroup leftSCG = new SpeedControllerGroup(frontLeft, backLeft);

  private final DifferentialDrive robotDrive = new DifferentialDrive(leftSCG, rightSCG);

  int counter = 0;

  private static DriveTrain instance;

  private DriveTrain(){
    init();
  }

  public static DriveTrain getInstance(){
    if (instance == null){
      instance = new DriveTrain();
    }

    return instance;
  }

  private void init(){

    robotDrive.setExpiration(1);
    robotDrive.setSafetyEnabled(true);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ArcadeDrive());
  }

  public void arcade(Joystick driver){

    double speed = -driver.getRawAxis(RobotMap.leftStickY);
    double turn = driver.getRawAxis(RobotMap.rightStickX);
    robotDrive.arcadeDrive(speed, turn, true);
  }

  public void stop(){
    robotDrive.stopMotor();
  }

}
