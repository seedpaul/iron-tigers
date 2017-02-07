package org.usfirst.frc.team4041.robot;

import org.usfirst.frc.team4041.robot.commands.ClimbWithController;
import org.usfirst.frc.team4041.robot.commands.DriveStraight;
import org.usfirst.frc.team4041.robot.commands.DriveCurve;
import org.usfirst.frc.team4041.robot.commands.FeedWithController;
import org.usfirst.frc.team4041.robot.commands.PickUpWithController;
import org.usfirst.frc.team4041.robot.commands.ShootWithController;
import org.usfirst.frc.team4041.robot.commands.UnloadWithController;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


public class OI {

	static final Joystick xbox = new Joystick(RobotMap.xboxController);
	static final JoystickButton buttonA = new JoystickButton(xbox, RobotMap.buttonA);
	static final JoystickButton buttonB = new JoystickButton(xbox, RobotMap.buttonB);
	static final JoystickButton buttonX = new JoystickButton(xbox, RobotMap.buttonX);
	static final JoystickButton buttonY = new JoystickButton(xbox, RobotMap.buttonY);
	static final JoystickButton buttonRightBumper = new JoystickButton(xbox, RobotMap.buttonBumperRight);
	static final JoystickButton buttonLeftBumper = new JoystickButton(xbox, RobotMap.buttonBumperLeft);
	
	static final JoystickButton buttonRightTrigger = new JoystickButton(xbox, RobotMap.rightTrigger);
	static final JoystickButton buttonLeftTrigger = new JoystickButton(xbox, RobotMap.leftTrigger);

    public void init() {
    	//put some code here if you like
    	buttonA.toggleWhenPressed(new PickUpWithController());
    	//buttonB.toggleWhenPressed(new DriveForward(15.0,0.3));
    	buttonB.toggleWhenPressed(new DriveCurve(24.0, 12.0, 0.4, 10.0));
    	buttonX.toggleWhenPressed(new ClimbWithController());
    	buttonY.toggleWhenPressed(new UnloadWithController());
    	buttonLeftBumper.toggleWhenPressed(new FeedWithController());
    	buttonRightBumper.toggleWhenPressed(new ShootWithController());
    }
    
    private static double deadzone(double d) {
        //whenever the controller moves LESS than the magic number, the 
        //joy stick is in the loose position so return zero - as if the 
        //joy stick was not moved
        if (Math.abs(d) < RobotMap.DEADZONE_MAGIC_NUMBER) {
            return 0;
        }

        //When the joy stick is used for a purpose (passes the if statements, 
        //hence not just being loose), do math
        //gets the sign of d, negative or positive
        return (d / Math.abs(d)) * ((Math.abs(d) - RobotMap.DEADZONE_MAGIC_NUMBER) / (1 - RobotMap.DEADZONE_MAGIC_NUMBER)); //scales it
    }

    public static double getLeftStickY() {
        return deadzone(-xbox.getRawAxis(RobotMap.leftStickY));
    }

    public static double getRightStickY() {
        return deadzone(-xbox.getRawAxis(RobotMap.rightStickY));
    }

	  public boolean getRightTrigger() {
	  double triggerValue = xbox.getRawAxis(RobotMap.rightTrigger);
	  if (triggerValue < 0) {
	      return true;
	  } else {
	      return false;
	  }
	}

    public boolean getLeftTrigger() {
        double triggerValue = xbox.getRawAxis(RobotMap.leftTrigger);
        if (triggerValue > 0) {
            return true;
        } else {
            return false;
        }
    }
}
