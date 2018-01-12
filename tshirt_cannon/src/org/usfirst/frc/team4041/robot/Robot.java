
package org.usfirst.frc.team4041.robot;

import org.usfirst.frc.team4041.robot.commands.CommandBase;
import org.usfirst.frc.team4041.robot.commands.TankDriveWithController;
import org.usfirst.frc.team4041.robot.subsystems.Cannon;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Robot extends IterativeRobot {

	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	Compressor c = new Compressor();

	@Override
	public void robotInit() {
		
		c.setClosedLoopControl(true);
		
		oi = new OI();
		CommandBase.init();
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {

		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	@Override
    public void teleopPeriodic() {
    	
        Scheduler.getInstance().run();

        if (((Subsystem) CommandBase.driveTrain).getCurrentCommand() == null) {
            Scheduler.getInstance().add(new TankDriveWithController());
        }
    	
    	if(c.getPressureSwitchValue()){
    		Cannon.lightOn();
    	}
    	else{
    		Cannon.lightOff();
    	}
    }

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
