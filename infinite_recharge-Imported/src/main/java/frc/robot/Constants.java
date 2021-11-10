/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * Add your docs here.
 */
public class Constants {
    public static final class DriveConstants {

        public static final boolean kLeftEncoderReversed = false;
        public static final boolean kRightEncoderReversed = true;
  
        public static final double kTrackwidthMeters = 0.61;
        public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(kTrackwidthMeters);
    
        public static final double kEncoderCPR = 2048;
        public static final double kWheelDiameterMeters = 0.15;
        public static final double kEncoderDistancePerPulse = (kWheelDiameterMeters * Math.PI) / kEncoderCPR;
    
        public static final boolean kGyroReversed = false;
    
        // These are example values only - DO NOT USE THESE FOR YOUR OWN ROBOT!
        // These characterization values MUST be determined either experimentally or theoretically
        // for *your* robot's drive.
        // The Robot Characterization Toolsuite provides a convenient tool for obtaining these
        // values for your robot.
        public static final double ksVolts = 0.0189;
        public static final double kvVoltSecondsPerMeter = 0.0192;
        public static final double kaVoltSecondsSquaredPerMeter = 0.0267;
    
        // Example value only - as above, this must be tuned for your drive!
        public static final double kPDriveVel = 0.000333;
    }

    public static final class AutoConstants {
        public static final double kMaxSpeedMetersPerSecond = 1;
        public static final double kMaxAccelerationMetersPerSecondSquared = 0.5;
    
        // Reasonable baseline values for a RAMSETE follower in units of meters and seconds
        public static final double kRamseteB = 2;
        public static final double kRamseteZeta = 0.7;
      }

    public static final class ElevatorPositions {
        public static final int Top =  768000; //752000 actual number;
        public static final int Hover = 375000;
        public static final int Increment = 192000;
        public static final int Home = 0;
    }
}
