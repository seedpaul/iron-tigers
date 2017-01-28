package org.usfirst.frc.team4041.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends SampleRobot {
	
	CameraServer server;
	RobotDrive robotDrive;
	
	static final Talon leftTalon = new Talon(RobotMap.leftDriveTalon);
	static final Talon rightTalon = new Talon(RobotMap.RightDriveTalon);
	static final VictorSP victor = new VictorSP(RobotMap.shooterVictor);
	
	static final Encoder leftEncoder = new Encoder(RobotMap.leftEncooderDIO1, RobotMap.leftEncooderDIO2, true, Encoder.EncodingType.k4X);
	static final Encoder rightEncoder = new Encoder(RobotMap.RightEncooderDIO1, RobotMap.RightEncooderDIO12, false, Encoder.EncodingType.k4X);
	static final Encoder shooterEncoder = new Encoder(RobotMap.shooterEncoderDIO1, RobotMap.shooterEncoderDIO2, true, Encoder.EncodingType.k4X);
	
	static final Joystick xbox = new Joystick(RobotMap.xboxController);
	static final ADXRS450_Gyro spiGyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
	//static final LiveWindow liveWindow = new LiveWindow();
	
	double axisR;
	double axisL;
	double trigger;
	double angle; 
	double rate;
	int throttle = 0;

	public void robotInit() {
		 server = CameraServer.getInstance();
		 server.startAutomaticCapture();
	}

	public Robot() {
		
		robotDrive = new RobotDrive(leftTalon, rightTalon);
		robotDrive.setExpiration(0.1);
		
		leftEncoder.setDistancePerPulse(0.00277778);
		rightEncoder.setDistancePerPulse(0.00277778);
		
		//LiveWindow.addSensor("Chassis", "gyro", spiGyro);

		try {
			spiGyro.reset();
			spiGyro.startLiveWindowMode();
		} catch (NullPointerException npe) {
			//eat this error
		}
	}

	// This function is called once each time the robot enters autonomous mode.
	public void autonomous() {
		// Put code here
	}

	// This function is called once each time the robot enters teleop mode.
	public void operatorControl() {
		robotDrive.setSafetyEnabled(true);
		
		while (isOperatorControl() && isEnabled()) {
			
			//Axis 3 is the Rear Triggers axis value Left = 0 up to 1 & Right = 0 down to -1
			trigger = xbox.getRawAxis(3);
			
			axisR = xbox.getRawAxis(5);
			axisL = xbox.getRawAxis(1);
			
			robotDrive.tankDrive(axisR, axisL);
			victor.set(trigger);
			logToConsole(true);
		}
	}
	
	private void logToConsole(boolean log){
		
		if(log){
			throttle++;
			if(throttle % 1000 == 0){
				
				// gyro output
				angle = Math.round(spiGyro.getAngle() * 100.0) / 100.0;
				rate =  Math.round(spiGyro.getRate() * 100.0) / 100.0;
				System.out.println("angle: " + angle);
				System.out.println("rate: " + rate);
				
				// encoder output
				System.out.println("left distance: " + leftEncoder.getDistance());
				System.out.println("right distance: " + rightEncoder.getDistance());
				System.out.println("shooter rate: " + shooterEncoder.getRate()); 
				
				//controller input
				System.out.println("right joystick: " + axisR);
				System.out.println("left joystick: " + axisL);
				System.out.println("triggers: " + trigger);
			}
		}
	}
	
	public void disable(){
		angle = 0;
		rate = 0;
	}
}