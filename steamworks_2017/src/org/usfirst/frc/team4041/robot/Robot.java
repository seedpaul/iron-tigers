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

public class Robot extends SampleRobot {
	CameraServer server;
	RobotDrive robotDrive;
	static final Talon leftTalon = new Talon(3);
	static final Talon rightTalon = new Talon(2);
	static final VictorSP victor = new VictorSP(6);
	
	static final Encoder leftEncoder = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
	static final Encoder rightEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
	static final Joystick xbox = new Joystick(0);
	double axisR;
	double axisL;
	double triggers;
	double angle; 
	double rate ;
	static final ADXRS450_Gyro spiGyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

	public void robotInit() {
		 server = CameraServer.getInstance();
		 server.startAutomaticCapture();
	}

	public Robot() {

		robotDrive = new RobotDrive(leftTalon, rightTalon);
		robotDrive.setExpiration(0.1);

		try {
			spiGyro.reset();
			spiGyro.startLiveWindowMode();
		} catch (NullPointerException npe) {
			// do nothing

		}
	}

	// This function is called once each time the robot enters autonomous mode.
	public void autonomous() {
		// Put code here
	}

	// This function is called once each time the robot enters teleop mode.
	public void operatorControl() {
		robotDrive.setSafetyEnabled(true);
		int throttle = 0;

		while (isOperatorControl() && isEnabled()) {
			
			//Axis 3 is the Rear Triggers axis value Left = 0 up to 1 & Right = 0 down to -1
			triggers = xbox.getRawAxis(3);
			
			axisR = xbox.getRawAxis(5);
			axisL = xbox.getRawAxis(1);
			robotDrive.tankDrive(axisR, axisL);
			throttle++;
			if(throttle % 1000 == 0){
				angle = Math.round(spiGyro.getAngle() * 100.0) / 100.0;
				rate =  Math.round(spiGyro.getRate() * 100.0) / 100.0;
				System.out.println("angle: " + angle);
				System.out.println("rate: " + rate);
			}
			victor.set(triggers);

		}
	}
	public void disable(){
		angle = 0;
		rate = 0;
	}
}