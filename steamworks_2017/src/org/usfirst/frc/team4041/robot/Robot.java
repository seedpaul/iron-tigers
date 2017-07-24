package org.usfirst.frc.team4041.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4041.robot.commands.*;


public class Robot extends IterativeRobot {
	
	CameraServer server;
	Command autonousCommand;
	Command driveCommand;
	SendableChooser<Command> autoChooser;
	SendableChooser<Command> driveChooser;
	
	public void robotInit() {
		
		 server = CameraServer.getInstance();
		 server.startAutomaticCapture(0);
		 server.startAutomaticCapture(1);
		 
		 CommandBase.init();
		 SmartDashboard.putData(Scheduler.getInstance());
		 
		 autoChooser = new SendableChooser<Command>();
		 autoChooser.addDefault("Drive Straight", new DriveStraight(5,0.4));
		 
		 driveChooser = new SendableChooser<Command>();
		 driveChooser.addDefault("Tank", new TankDriveWithController());
		 driveChooser.addDefault("Arcade", new ArcadeDriveWithController());
		 
//		 autoChooser = new SendableChooser<CommandGroup>();
//		 autoChooser.addDefault("Drive Straight", new autonomous_straight());
//		 
//		 autoChooser.addObject("Turn Left", new autonomous_left(false));
//		 autoChooser.addObject("Turn Left w/shooter", new autonomous_left(true));
//		 
//		 autoChooser.addObject("Turn Right", new autonomous_right(false));
//		 autoChooser.addObject("Turn Right w/shooter", new autonomous_right(true));
		 
		 SmartDashboard.putData("Autonomous Mode Selection:", autoChooser);
		 SmartDashboard.putData("Drive Mode Selection:", driveChooser);
	}

	public Robot() {
		
	}
    public void autonomousInit() {
    	//do autonomous init stuff here;
    	autonousCommand = (Command) autoChooser.getSelected();
    	autonousCommand.start();
    }

    public void autonomousPeriodic() {
    	//do periodic autonomous stuff here
    	Scheduler.getInstance().run();
    }

    public void teleopInit() {
        
        driveCommand = (Command) driveChooser.getSelected();
        driveCommand.start();
        
        Command initCamera = new RotateCameraBack();
        initCamera.start();
        
        SmartDashboard.putData(Scheduler.getInstance());
        
    }

    public void teleopPeriodic() {
    	
        Scheduler.getInstance().run();

        if (((Subsystem) CommandBase.driveTrain).getCurrentCommand() == null) {
            Scheduler.getInstance().add(driveCommand);
        }
    }

    public void disabledInit() {

    }

    public void testPeriodic() {

    }
	
	public void disable(){

	}
}