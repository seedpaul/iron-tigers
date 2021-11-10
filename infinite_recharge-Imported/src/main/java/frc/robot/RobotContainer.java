/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.*;
import frc.robot.commands.groups.*;
import frc.robot.subsystems.*;
import frc.robot.subsystems.components.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//import edu.wpi.first.wpilibj2.command.RunCommand;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  //The robot's subsystems and commands are defined here...
  //components
  public final XboxController driver = new XboxController(RobotMap.xboxControllerDriver);
  private final XboxController assist = new XboxController(RobotMap.xboxControllerAssist);

  private final Camera camera = new Camera();
  // private final ColorSensor colorSensor = new ColorSensor();
  private final RangeSensors rangeSensors = new RangeSensors();
  private final LimeLight limeLight = new LimeLight();
  private final NavX navX = new NavX();

  public final BarLeveler barLeveler = new BarLeveler(navX);
  public final DriveTrain driveTrain = new DriveTrain(navX);
  private final Elevator elevator = new Elevator();
  // private final ElevatorArm elevatorArm = new ElevatorArm();
  private final Indexer indexer = new Indexer(rangeSensors, assist);
  private final IntakeElbow intakeElbow = new IntakeElbow();
  private final IntakeWheels intakeWheels = new IntakeWheels(assist);
  private final Turret turret = new Turret();
  private final Shooter shooter = new Shooter();
  private final Bombardier bombardier = new Bombardier(indexer, turret, shooter, limeLight, intakeWheels);


  private final SequentialCommandGroup RightTrench = new RightTrench(bombardier, driveTrain, intakeWheels, intakeElbow);
  private final SequentialCommandGroup StraightBack = new StraightBack(bombardier, driveTrain);
  private final SequentialCommandGroup GoToLeft = new GoToLeft(bombardier, driveTrain, intakeWheels, intakeElbow);
  private final SequentialCommandGroup ReverseTesting = new ReverseTesting(driveTrain);

  private SendableChooser<SequentialCommandGroup> chooser = new SendableChooser<>();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    camera.video();

    chooser.setDefaultOption("RightTrench", RightTrench);
    chooser.addOption("StraightBack", StraightBack);
    chooser.addOption("GoToLeft", GoToLeft);
    chooser.addOption("ReverseTesting", ReverseTesting);

    SmartDashboard.putData(chooser);

  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    JoystickButton buttonBumperRight_dr = new JoystickButton(driver,RobotMap.buttonBumperRight);
    JoystickButton buttonA_dr = new JoystickButton(driver,RobotMap.buttonA);
    JoystickButton buttonY_dr = new JoystickButton(driver,RobotMap.buttonY);

    buttonBumperRight_dr.whenPressed((edu.wpi.first.wpilibj2.command.Command)new TurretStartTargeting(bombardier));
    buttonBumperRight_dr.whenReleased((edu.wpi.first.wpilibj2.command.Command)new TurretEndTargeting(bombardier));

    buttonA_dr.whenPressed((edu.wpi.first.wpilibj2.command.Command)new ElevatorDown(elevator));
    buttonY_dr.whenPressed((edu.wpi.first.wpilibj2.command.Command)new ElevatorUp(elevator));

    JoystickButton buttonA_as = new JoystickButton(assist,RobotMap.buttonA);
    JoystickButton buttonY_as = new JoystickButton(assist,RobotMap.buttonY);
    JoystickButton buttonX_as = new JoystickButton(assist,RobotMap.buttonX);
    JoystickButton buttonB_as = new JoystickButton(assist,RobotMap.buttonB);

    JoystickButton buttonBumperRight_as = new JoystickButton(assist,RobotMap.buttonBumperRight);
    JoystickButton buttonBumperLeft_as = new JoystickButton(assist,RobotMap.buttonBumperLeft);

    buttonX_as.whenPressed((edu.wpi.first.wpilibj2.command.Command)new IntakeWheelsOn(intakeWheels));
    buttonB_as.whenPressed((edu.wpi.first.wpilibj2.command.Command)new IntakeWheelsOff(intakeWheels));

    buttonA_as.whenPressed((edu.wpi.first.wpilibj2.command.Command)new IntakeDown(intakeElbow));
    buttonY_as.whenPressed((edu.wpi.first.wpilibj2.command.Command)new IntakeHome(intakeElbow));

    buttonBumperRight_as.whenPressed((edu.wpi.first.wpilibj2.command.Command)new IntakeWheelsBack(intakeWheels));
    buttonBumperRight_as.whenReleased((edu.wpi.first.wpilibj2.command.Command)new IntakeWheelsPrevState(intakeWheels));

    buttonBumperLeft_as.whenPressed((edu.wpi.first.wpilibj2.command.Command)new IndexerReverse(indexer));
    buttonBumperLeft_as.whenReleased((edu.wpi.first.wpilibj2.command.Command)new IndexerStop(indexer));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    // return m_autoCommand;
    return chooser.getSelected();
    //return new RightTrench(bombardier, driveTrain, intakeWheels, intakeElbow);
  }

  public void disabledLEDS(){
    limeLight.ledOff();
  }

  public void enableAutoIndexing(){
    indexer.enabledAutoIndexing(true);
  }
}
