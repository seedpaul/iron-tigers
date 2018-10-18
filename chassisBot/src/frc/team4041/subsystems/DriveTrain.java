package frc.team4041.subsystems;


import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4041.commands.ArcadeDrive;

public class DriveTrain extends Subsystem {

    private Spark leftRear = new Spark(1);
    private Spark leftFront = new Spark(0);

    private Spark rightRear = new Spark(3);
    private Spark rightFront = new Spark(2);
    private SpeedControllerGroup leftSCG = new SpeedControllerGroup(leftFront,leftRear);
    private SpeedControllerGroup rightSCG = new SpeedControllerGroup(rightFront,rightRear);
    private Encoder leftEncoder = new edu.wpi.first.wpilibj.Encoder(2,3, true, CounterBase.EncodingType.k4X);
    private Encoder rightEncoder = new edu.wpi.first.wpilibj.Encoder(4,5,false, CounterBase.EncodingType.k4X);
    private ADXRS450_Gyro spiGyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

    private DifferentialDrive drive = new DifferentialDrive(leftSCG,rightSCG);
    private static DriveTrain instance;

    public static DriveTrain getInstance(){
        if(instance == null){
            instance = new DriveTrain();
        }
        return instance;
    }

    private DriveTrain(){
        initialize();
    }

    private void initialize(){
        drive.setExpiration(1);
        double circumference = 18.8496;
        double pulsesPerRevolution = 360;
        double distancePerPulse = circumference/pulsesPerRevolution;

        leftEncoder.setDistancePerPulse(distancePerPulse);
        rightEncoder.setDistancePerPulse(distancePerPulse);

        leftEncoder.reset();
        rightEncoder.reset();

        drive.setSafetyEnabled(false);
        try{
            spiGyro.reset();
            spiGyro.calibrate();
        }catch (NullPointerException npe){
            //eat this error
        }
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ArcadeDrive());
    }

    public void arcadeDrive (Joystick driverStick, int moveAxis, int turnAxis){
        double move = -driverStick.getRawAxis(moveAxis);
        double turn = driverStick.getRawAxis(turnAxis);

        drive.arcadeDrive(move,turn,true);

        dataForDashboard();
    }

    public void dataForDashboard(){
        SmartDashboard.putData("leftEncoder",leftEncoder);
        SmartDashboard.putData("rightEncoder",leftEncoder);
        SmartDashboard.putData("Gyro", spiGyro);
    }
}

