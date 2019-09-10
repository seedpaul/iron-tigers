package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class IntakeWheels extends Subsystem {

  private static final TalonSRX intakeWheelsTalon = new TalonSRX(20);
  private static IntakeWheels instance;

  private IntakeWheels(){
    init();
  }

  public static IntakeWheels getInstance(){
    if (instance==null){
      instance = new IntakeWheels();
    }
    return instance;
  }

  private void init(){

    intakeWheelsTalon.configFactoryDefault();
    intakeWheelsTalon.set(ControlMode.PercentOutput,0);
    intakeWheelsTalon.setNeutralMode(NeutralMode.Brake);

    intakeWheelsTalon.configNominalOutputForward(0,30);
    intakeWheelsTalon.configNominalOutputReverse(0,30);
    intakeWheelsTalon.configPeakOutputForward(1, 30);
    intakeWheelsTalon.configPeakOutputReverse(-1, 30);
    
    intakeWheelsTalon.configPeakCurrentLimit(15, 30);
    intakeWheelsTalon.configPeakCurrentDuration(120, 30);
    intakeWheelsTalon.configContinuousCurrentLimit(3, 30);
    intakeWheelsTalon.enableCurrentLimit(true);

  }

  public void injest(){
    intakeWheelsTalon.set(ControlMode.PercentOutput, 0.5);
  }

  public void eject(){
    intakeWheelsTalon.set(ControlMode.PercentOutput, -1.0);
  }

  public void stop(){
    intakeWheelsTalon.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}