package org.usfirst.frc.team4041.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4041.robot.commandGroups.auto.AutonomousAutoZone;
import org.usfirst.frc.team4041.robot.commandGroups.auto.AutonomousCenterLeftSwitch;
import org.usfirst.frc.team4041.robot.commandGroups.auto.AutonomousCenterRightSwitch;
import org.usfirst.frc.team4041.robot.commandGroups.auto.AutonomousLeftLeftScale;
import org.usfirst.frc.team4041.robot.commandGroups.auto.AutonomousLeftLeftSwitch;
import org.usfirst.frc.team4041.robot.commandGroups.auto.AutonomousRightRightScale;
import org.usfirst.frc.team4041.robot.commandGroups.auto.AutonomousRightRightSwitch;
import org.usfirst.frc.team4041.robot.commands.teleop.ArcadeDrive;
import org.usfirst.frc.team4041.robot.commands.teleop.CommandBase;

public class Robot extends IterativeRobot {

	Command driveCommand;
	CameraServer server;
	SendableChooser<String> positionChooser;

	public void robotInit() {

		server = CameraServer.getInstance();
		server.startAutomaticCapture(0);

		CommandBase.init();
		SmartDashboard.putData(Scheduler.getInstance());

		positionChooser = new SendableChooser<String>();

		positionChooser.addObject("Left", "L");
		positionChooser.addDefault("Center", "C");
		positionChooser.addObject("Right", "R");

		SmartDashboard.putData("Position Selection:", positionChooser);

	}

	public Robot() {

	}

	public void autonomousInit() {
		
		int retries = 100;//try 100 times
        String gameData = DriverStation.getInstance().getGameSpecificMessage();
        while (gameData.length() < 2 && retries > 0) {
            retries--;
            try {
                Thread.sleep(5);
            } catch (InterruptedException ie) {
                // Just ignore the interrupted exception
            }
            gameData = DriverStation.getInstance().getGameSpecificMessage();
        }
        
        if (gameData.length() > 2) {
    		CommandGroup autoCommand = autoSelection(gameData, positionChooser.getSelected());
    		autoCommand.start(); 	
        }else {
        	//we ran out of retries
        	//and need to do something to at least score auto zone points
        }  
		
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {

		driveCommand = (Command) new ArcadeDrive();
		driveCommand.start();
		
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

	public void disable() {

	}

	public CommandGroup autoSelection(String FMSData, String Position) {

		CommandGroup RunThisAutoCommand = null;

		if (Position.equals("C")) {
			// we're in the center
			if (FMSData.substring(0, 1).equals("L")) {
				// run auto center-left
				RunThisAutoCommand = new AutonomousCenterLeftSwitch();
			} else {
				// run auto center-right
				RunThisAutoCommand = new AutonomousCenterRightSwitch();
			}

		} else if (Position.equals("R")) {
			// We're on the right
			if (FMSData.substring(1, 2).equals("R")) {
				// run auto right scale
				RunThisAutoCommand = new AutonomousRightRightScale();
			} else {
				if (FMSData.substring(0, 1).equals("R")) {
					// run auto right switch
					RunThisAutoCommand = new AutonomousRightRightSwitch();
				} else {
					// run auto drive to zone
					RunThisAutoCommand = new AutonomousAutoZone();
				}
			}

		} else {
			// w're on the left
			if (FMSData.substring(1, 2).equals("L")) {
				// run auto left scale
				RunThisAutoCommand = new AutonomousLeftLeftScale();

			} else {
				if (FMSData.substring(0, 1).equals("L")) {
					// run auto left switch
					RunThisAutoCommand = new AutonomousLeftLeftSwitch();
				} else {
					// run auto drive to zone
					RunThisAutoCommand = new AutonomousAutoZone();
				}
			}

		}
		return RunThisAutoCommand;
	}
}