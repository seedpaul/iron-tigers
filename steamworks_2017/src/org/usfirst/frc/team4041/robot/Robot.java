package org.usfirst.frc.team4041.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
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
	SendableChooser<CommandBase> autoChooser;

	public void robotInit() {
		
		 server = CameraServer.getInstance();
		 server.startAutomaticCapture();
		 
		 CommandBase.init();
		 SmartDashboard.putData(Scheduler.getInstance());
		 
		 autoChooser = new SendableChooser<CommandBase>();
		 autoChooser.addDefault("Drive Straight", new DriveStraight(5.2, 0.55));
		 
		 autoChooser.addObject("Turn Left", new DriveCurveLeft());
		 autoChooser.addObject("Turn Right", new DriveCurveRight());
		 
		 SmartDashboard.putData("Autonomous Mode Selection:", autoChooser);
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
    	
        Command driveTrainControls = new DriveWithController();
        driveTrainControls.start();
        
        Command initCamera = new RotateCameraBack();
        initCamera.start();
        
        SmartDashboard.putData(Scheduler.getInstance());
        
    }

    public void teleopPeriodic() {
    	
        Scheduler.getInstance().run();

        if (((Subsystem) CommandBase.driveTrain).getCurrentCommand() == null) {
            Scheduler.getInstance().add(new DriveWithController());
        }
    }

    public void disabledInit() {

    }

    public void testPeriodic() {

    }
	
	public void disable(){

	}
}