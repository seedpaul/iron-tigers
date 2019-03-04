
// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.subsystems;
// import frc.robot.RobotMap;
// import frc.robot.IntakeClawPositions;
// import com.ctre.phoenix.motorcontrol.can.TalonSRX;
// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// import com.ctre.phoenix.motorcontrol.NeutralMode;
// import edu.wpi.first.wpilibj.Encoder;

// import edu.wpi.first.wpilibj.command.Subsystem;

// public class IntakeClaw extends Subsystem {

//   private static final TalonSRX intakeClawSRX = new TalonSRX(RobotMap.TalonIntakeClaw);
//   private static final TalonSRX intakeflipper = new TalonSRX(RobotMap.TalonIntakeClawFlipper);
//   private static final Encoder flipperEncoder = new Encoder(RobotMap.flipperEncoderChannelA, RobotMap.flipperEncoderChannelB, true, Encoder.EncodingType.k4X);

//   private static final int flipper_home = 0;
//   private static final int flipper_extend = 475;

//   private static IntakeClaw instance;

//   private IntakeClaw(){
//     init();
//   }

//   private void init(){ 
    
//     //************** claw**************/
//     intakeClawSRX.configFactoryDefault();
//     intakeClawSRX.setSensorPhase(false);
//     intakeClawSRX.set(ControlMode.PercentOutput,0);
//     intakeClawSRX.setNeutralMode(NeutralMode.Brake);
//     intakeClawSRX.configForwardSoftLimitThreshold(IntakeClawPositions.getMax());
//     intakeClawSRX.configReverseSoftLimitThreshold(IntakeClawPositions.getMin());
//     intakeClawSRX.configForwardSoftLimitEnable(true);
//     intakeClawSRX.configReverseSoftLimitEnable(true);
//     intakeClawSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
//     intakeClawSRX.configAllowableClosedloopError(0,75,30);

// 		intakeClawSRX.config_kF(0, 0.0, 30);
// 		intakeClawSRX.config_kP(0, 0.85, 30);
// 		intakeClawSRX.config_kI(0, 0.0, 30);
//     intakeClawSRX.config_kD(0, 1.0, 30);
    
//     intakeClawSRX.configNominalOutputForward(0,30);
//     intakeClawSRX.configNominalOutputReverse(0,30);
//     intakeClawSRX.configPeakOutputForward(1, 30);
//     intakeClawSRX.configPeakOutputReverse(-1, 30);

//     intakeClawSRX.configPeakCurrentLimit(9, 30);
//     intakeClawSRX.configPeakCurrentDuration(120, 30);
//     intakeClawSRX.configContinuousCurrentLimit(1, 30);
//     intakeClawSRX.enableCurrentLimit(true);

//     intakeClawSRX.setSelectedSensorPosition(IntakeClawPositions.getMax(),0,30);

//     //************** flipper**************/
//     intakeflipper.configFactoryDefault();

//     intakeflipper.set(ControlMode.PercentOutput,0);
//     intakeflipper.setNeutralMode(NeutralMode.Brake);
//     intakeflipper.configNominalOutputForward(0,30);
//     intakeflipper.configNominalOutputReverse(0,30);
//     intakeflipper.configPeakOutputForward(1, 30);
//     intakeflipper.configPeakOutputReverse(-1, 30);

//     intakeflipper.configPeakCurrentLimit(5, 30);
//     intakeflipper.configPeakCurrentDuration(120, 30);
//     intakeflipper.configContinuousCurrentLimit(1, 30);
//     intakeflipper.enableCurrentLimit(true); 

//     flipperEncoder.reset();
//   }

//   public static IntakeClaw getInstance(){
//     if (instance==null){
//       instance = new IntakeClaw();
//     }
//     return instance;
//   }

//   public void clawOpen(){
//     intakeClawSRX.set(ControlMode.Position, IntakeClawPositions.open());
//   }

//   public void clawClose(){
//     intakeClawSRX.set(ControlMode.Position, IntakeClawPositions.close());
//   }

//   public void clawReleaseBall(){
//     intakeClawSRX.set(ControlMode.Position, IntakeClawPositions.releaseBall());
//   }

//   public void clawStop(){
//     intakeClawSRX.set(ControlMode.PercentOutput, 0.0);
//   }

//   public boolean flipperHome(){

//     boolean done = false;
//     double currentDistance = flipperEncoder.getDistance(); 

//     if(currentDistance > flipper_home){
//       intakeflipper.set(ControlMode.PercentOutput, 1.0);
//     }
//     else{
//       done = true;
//     }
//     return done;
//   }

//   public boolean flipperExtend(){

//     boolean done = false;
//     double currentDistance = flipperEncoder.getDistance(); 

//     if(currentDistance < flipper_extend){
//       intakeflipper.set(ControlMode.PercentOutput, -1.0);
//     }
//     else{
//       done = true;
//     }
//     return done;
//   }

//   public void flipperStop(){
//     intakeflipper.set(ControlMode.PercentOutput, 0.0);
//   }

//   public Encoder getFlipperEncoder(){
//     return flipperEncoder;
//   }

//   @Override
//   public void initDefaultCommand() {
//     // Set the default command for a subsystem here.
//     // setDefaultCommand(new MySpecialCommand());
//   }
// }
