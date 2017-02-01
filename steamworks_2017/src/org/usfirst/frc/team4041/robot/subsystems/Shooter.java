package org.usfirst.frc.team4041.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;

import org.usfirst.frc.team4041.robot.RobotMap;
import org.usfirst.frc.team4041.robot.commands.ShootWithController;

public class Shooter extends Subsystem {

	static final VictorSP shooterVictor = new VictorSP(RobotMap.shooterVictor);
	static final Encoder shooterEncoder = new Encoder(RobotMap.shooterEncoderDIO1, RobotMap.shooterEncoderDIO2, true, Encoder.EncodingType.k4X);
	
    private static Shooter instance;
    private static final int spinningThreshold = 100;
    
    private Shooter() {
    	// do some stuff here it need be!
    	initialize();
    }
    
    public static Shooter getInstance() {
    	
        if (instance == null) {
            instance = new Shooter();
        }
        return instance;
    }
    
    public void initialize() {
    	
    	shooterEncoder.reset();
    	shooterEncoder.setDistancePerPulse(1);
    }

    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        setDefaultCommand(new ShootWithController());
    }
    
    public void runShooter(double speed){
    	
    	shooterVictor.set(speed);
    }
    
    public void reverseShooter(double speed){
    	
    	shooterVictor.set(speed);
    }
    
    public double getShooterSpeed(){
    	
    	return shooterEncoder.getRate();
    }
    
    public boolean isSpinning(){
    	
    	return getShooterSpeed() > spinningThreshold;
    }
    
    
    public void stopShooter(){
    	
    	shooterVictor.set(RobotMap.STOP);
    }
}

