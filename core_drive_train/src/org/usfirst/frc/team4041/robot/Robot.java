package org.usfirst.frc.team4041.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4041.robot.commands.*;


public class Robot extends IterativeRobot {
	
	Command driveCommand;
	SendableChooser<Command> driveChooser;
	
	public void robotInit() {
		 
		 CommandBase.init();
		 SmartDashboard.putData(Scheduler.getInstance());

	}

	public Robot() {
		
	}
    public void autonomousInit() {

    }

    public void autonomousPeriodic() {

    }

    public void teleopInit() {
        
        driveCommand = (Command) new ArcadeDriveWithController();
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
	
	public void disable(){

	}
}