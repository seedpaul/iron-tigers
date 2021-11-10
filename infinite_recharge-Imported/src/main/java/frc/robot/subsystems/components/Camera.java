/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.components;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

public class Camera extends SubsystemBase {
  /**
   * Creates a new Camera.
   */
	CameraServer server;
	UsbCamera cam;

  public Camera(){
    init();
  }

  private void init()
  {
    server = CameraServer.getInstance();
		cam = server.startAutomaticCapture();
		cam.setResolution(320, 240);
		cam.setFPS(20);
  }


  @Override
  public void periodic() {
    super.periodic();
    // This method will be called once per scheduler run
  }

  public void video(){

  }
}

