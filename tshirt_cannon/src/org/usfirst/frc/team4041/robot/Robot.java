
package org.usfirst.frc.team4041.robot;

import org.usfirst.frc.team4041.robot.commands.CommandBase;
import org.usfirst.frc.team4041.robot.commands.TankDriveWithController;
import org.usfirst.frc.team4041.robot.subsystems.Cannon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Robot extends IterativeRobot {

	public static OI oi;
    private Cannon cannon;
	@Override
	public void robotInit() {
		
		oi = new OI();
		CommandBase.init();
		cannon = Cannon.getInstance();
		cannon.c.setClosedLoopControl(true);
	}

	@Override
	public void disabledInit() {}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {}

	@Override
	public void autonomousPeriodic() {}

	@Override
	public void teleopInit() {}

	@Override
    public void teleopPeriodic() {
    	
        Scheduler.getInstance().run();

        if (((Subsystem) CommandBase.driveTrain).getCurrentCommand() == null) {
            Scheduler.getInstance().add(new TankDriveWithController());
        }
        
		if(cannon.c.getPressureSwitchValue()){
			cannon.lightOn();
		} else{
			cannon.lightOn();
		}
    }

	@Override
	public void testPeriodic() {}
}
