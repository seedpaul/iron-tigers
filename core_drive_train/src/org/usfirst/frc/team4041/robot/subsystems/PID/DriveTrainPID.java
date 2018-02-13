package org.usfirst.frc.team4041.robot.subsystems.PID;

import org.usfirst.frc.team4041.robot.RobotMap;
import org.usfirst.frc.team4041.robot.commands.teleop.ArcadeDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrainPID extends PIDSubsystem {

	static final WPI_TalonSRX leftFront = new WPI_TalonSRX(RobotMap.leftFrontDriveTalonSRX);
	static final WPI_TalonSRX leftRear = new WPI_TalonSRX(RobotMap.leftRearDriveTalonSRX);
	static final WPI_TalonSRX rightFront = new WPI_TalonSRX(RobotMap.rightFrontDriveTalonSRX);
	static final WPI_TalonSRX rightRear = new WPI_TalonSRX(RobotMap.rightRearDriveTalonSRX);

	static final SpeedControllerGroup drivetrainLeft = new SpeedControllerGroup(leftFront, leftRear);
	static final SpeedControllerGroup drivetrainRight = new SpeedControllerGroup(rightFront, rightRear);
	static final DifferentialDrive robotDrive = new DifferentialDrive(drivetrainLeft, drivetrainRight);

	// ToDo: put encoder locations in RobotMap
	static final Encoder leftEncoder = new Encoder(RobotMap.leftEncoderPt1, RobotMap.leftEncoderPt2, true, Encoder.EncodingType.k4X);
	static final Encoder rightEncoder = new Encoder(RobotMap.rightEncoderPt1, RobotMap.rightEncoderPt2, false, Encoder.EncodingType.k4X);
	static final ADXRS450_Gyro spiGyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

	//static final RangeFinder rangeFinder = RangeFinder.getInstance();
	
	private static double minInput = -180.0;
	private static double maxInput = 180.0;
	
	private static double minOutput = -0.95;
	private static double maxOutput = 0.95;
	private static double absoluteTolerance = 1.0;
	private static double setpoint = 0.0;
	private static double speed = 0.65;

	private static double p = 0.0;
	private static double i = 0.0;
	private static double d = 0.0;
	private static double f = 0.0;

	private static DriveTrainPID instance;

	private DriveTrainPID() {

		super("drivetrain", p, i, d, f);
		initialize();
	}

	public static DriveTrainPID getInstance() {
		if (instance == null) {
			instance = new DriveTrainPID();
		}
		return instance;
	}

	public void initialize() {
		
		//set up  and enable pid controller
		this.setAbsoluteTolerance(absoluteTolerance);
		this.getPIDController().setContinuous(true);
		this.setSetpoint(setpoint);
		this.setInputRange(minInput, maxInput);
		this.setOutputRange(minOutput, maxOutput);
		this.enable();
		
		//set up encoders
		double circumference = 18.8496;
		double pulsesPerRevolution = 360;
		double distancePerPulse = circumference / pulsesPerRevolution;

		leftEncoder.setDistancePerPulse(distancePerPulse);
		rightEncoder.setDistancePerPulse(distancePerPulse);

		// ToDo: what the hell is set saftey enabled?
		robotDrive.setSafetyEnabled(false);
		robotDrive.setExpiration(1);

		try {
			spiGyro.reset();
			spiGyro.calibrate();
		} catch (NullPointerException npe) {
			// eat this error
		}
		addInfoToDashBoard();
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
	}

	public void autoDrive(double speed, double rotation) {
		addInfoToDashBoard();
		robotDrive.curvatureDrive(speed, rotation, false);
	}

	public void tankDrive(double leftSpeed, double rightSpeed) {
		addInfoToDashBoard();
		this.disable();
		robotDrive.tankDrive(leftSpeed, rightSpeed);
	}

	public void arcadeDrive(Joystick driverController, int moveAxis, int turnAxis) {
		addInfoToDashBoard();
		this.disable();
		robotDrive.arcadeDrive(-driverController.getRawAxis(moveAxis), (driverController.getRawAxis(turnAxis)), true);
	}

	private void addInfoToDashBoard() {

		//SmartDashboard.putNumber("Ultrasonic Distance", rangeFinder.getSensorDistance());
		SmartDashboard.putData("Gyro", spiGyro);
		SmartDashboard.putData("leftEncoder", leftEncoder);
		SmartDashboard.putData("rightEncoder", rightEncoder);
		//SmartDashboard.putNumber("Claw Angle", Math.round(clawExtend.getAngle()));
	}

	public void stop() {
		robotDrive.stopMotor();
	}

	public double getLeftEncoderDistance() {
		return leftEncoder.getDistance();
	}

	public double getRightEncoderDistance() {
		return rightEncoder.getDistance();
	}

	public void resetRightEncoder() {
		rightEncoder.reset();
	}

	public void resetLeftEncoder() {
		leftEncoder.reset();
	}

	public double getGyroAngle() {
		return spiGyro.getAngle();
	}

	public void resetGyro() {
		spiGyro.reset();
	}

	@Override
	protected double returnPIDInput() {
		return spiGyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		robotDrive.curvatureDrive(speed, output, false);
	}
}
