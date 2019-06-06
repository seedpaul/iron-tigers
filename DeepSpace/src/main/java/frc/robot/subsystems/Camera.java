package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Camera extends Subsystem {

  private UsbCamera camLeft = new UsbCamera("CamLeft", 0);//CameraServer.getInstance().startAutomaticCapture("CamLeft", 0);
  private UsbCamera camRight = new UsbCamera("CamRight", 1); //CameraServer.getInstance().startAutomaticCapture("CamRight", 1);
  private final VideoSink sink = CameraServer.getInstance().addSwitchedCamera("CamLeft");//.getServer();

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

  private void init()
  {

    // System.out.println("camera init");

    camLeft.setResolution(320,240);
    camRight.setResolution(320,240);

    camLeft.setFPS(15);
    camRight.setFPS(15);

    camLeft.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
    camRight.setConnectionStrategy(ConnectionStrategy.kKeepOpen);

    //default to left camera
    sink.setSource(camLeft);
  }

  public void showCameraLeft(){
    sink.setSource(camLeft);
  }

  public void showCameraRight(){
    sink.setSource(camRight);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

}
