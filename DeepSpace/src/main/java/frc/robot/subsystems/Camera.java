/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Camera extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private UsbCamera camLeft;
  private UsbCamera camRight;
  private VideoSink sink;

  private static Camera instance;
  

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

    System.out.println("camera init");

    camLeft = CameraServer.getInstance().startAutomaticCapture("CamLeft", 0);
    camRight = CameraServer.getInstance().startAutomaticCapture("CamRight", 1);

    camLeft.setResolution(640,480);
    camRight.setResolution(640,480);

    camLeft.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
    camRight.setConnectionStrategy(ConnectionStrategy.kKeepOpen);

    sink = CameraServer.getInstance().getServer();

    sink.setSource(camLeft);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void showCameraLeft(){
    System.out.println("showLeft");
    sink.setSource(camLeft);
  }

  public void showCameraRight(){
    System.out.println("showRight");
    sink.setSource(camRight);
  }

}
