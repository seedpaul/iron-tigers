/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.*;

/**
 * Add your docs here.
 */
public class Camera extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private UsbCamera camLeft = CameraServer.getInstance().startAutomaticCapture("CamLeft", 0);
  private UsbCamera camRight = CameraServer.getInstance().startAutomaticCapture("CamRight", 1);
  
  private static Camera instance;

  private NetworkTableInstance inst;
  private NetworkTable table;
  private NetworkTableEntry entry;
  

  private Camera(){
    init();
  }

  public static Camera getInstance(){
    if (instance == null){
      instance = new Camera();
    }

    return instance;
  }

  private void init(){

    inst = NetworkTableInstance.getDefault();
    table = inst.getTable("");
    entry = table.getEntry("CameraSelection");
    entry.setString(camRight.getName());

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void showCameraLeft(){
    System.out.println("showLeft");
    entry.setString(camLeft.getName());
    table.notify();
  }

  public void showCameraRight(){
    System.out.println("showRight");
    entry.setString(camRight.getName());
    table.notify();
  }

}
