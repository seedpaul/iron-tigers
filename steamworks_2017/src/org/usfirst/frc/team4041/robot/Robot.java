package org.usfirst.frc.team4041.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4041.robot.commands.*;


public class Robot extends IterativeRobot {
	
	CameraServer server;
	
//	static final VictorSP waterfallVictor = new VictorSP(RobotMap.waterfallVictor);
//	static final VictorSP pickerVictor = new VictorSP(RobotMap.pickerVictor);

	public void robotInit() {
		
		 server = CameraServer.getInstance();
		 server.startAutomaticCapture();
		 
		 CommandBase.init();
		 SmartDashboard.putData(Scheduler.getInstance());
	}

	public Robot() {
		
	}
    public void autonomousInit() {
    	//do autonomous init stuff here;
    }

    public void autonomousPeriodic() {
    	//do periodic autonomous stuff here
    }

    public void teleopInit() {
    	
        Command driveTrainControls = new DriveWithController();
        driveTrainControls.start();
        
        Command initCamera = new RotateCameraBack();
        initCamera.start();
        
        SmartDashboard.putData(Scheduler.getInstance());
        
//        Command shoot = new ShootWithController();
//        //shoot.start();
//        
//        Command climb = new ClimbWithController();
//        //climb.start();
//        
//        Command feedShooter = new FeedWithController();
//        //feedShooter.start();
//        
//        Command collect = new PickUpWithController();
//        //collect.start();
//        
//        Command dump = new UnloadWithController();
        //dump.start();
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