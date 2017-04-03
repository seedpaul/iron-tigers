package org.usfirst.frc.team4041.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4041.robot.commandGroups.*;
import org.usfirst.frc.team4041.robot.commands.*;


public class Robot extends IterativeRobot {
	
	CameraServer server;
	Command autonousCommand;
//	SendableChooser<CommandGroup> autoChooser;
	SendableChooser<Command> autoChooser;
	
	public void robotInit() {
		
		 server = CameraServer.getInstance();
		 server.startAutomaticCapture();
		 //server.startAutomaticCapture(1);
		 
		 CommandBase.init();
		 SmartDashboard.putData(Scheduler.getInstance());
		 
		 autoChooser = new SendableChooser<Command>();
		 autoChooser.addDefault("Drive Straight", new DriveStraight(5,0.4));
		 
//		 autoChooser = new SendableChooser<CommandGroup>();
//		 autoChooser.addDefault("Drive Straight", new autonomous_straight());
//		 
//		 autoChooser.addObject("Turn Left", new autonomous_left(false));
//		 autoChooser.addObject("Turn Left w/shooter", new autonomous_left(true));
//		 
//		 autoChooser.addObject("Turn Right", new autonomous_right(false));
//		 autoChooser.addObject("Turn Right w/shooter", new autonomous_right(true));
		 
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