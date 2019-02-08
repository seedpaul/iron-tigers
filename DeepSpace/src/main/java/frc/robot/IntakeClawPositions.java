/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class IntakeClawPositions {
    
    private static int currentPosition;

    private static int load_release_HatchPanel = 0;//fully closed
    private static int holdHatchPanel = 0;
    private static int clampBall = 0;
    private static int releaseBall = 0;
    private static int loadBall = 0;//fully open

    public static int getMax(){
        return loadBall;
    }

    public static int getMin(){
        return load_release_HatchPanel;
    }

    public static int loadHatchPanel(){
        currentPosition = load_release_HatchPanel;
        return load_release_HatchPanel;
        
    }

    public static int holdHatchPanel(){
        currentPosition = load_release_HatchPanel;
        return holdHatchPanel;
    }

    public static int clampBall(){
        currentPosition = load_release_HatchPanel;
        return clampBall;
    }

    public static int releaseBall(){
        currentPosition = load_release_HatchPanel;
        return releaseBall;
    }

    public static int loadBall(){
        currentPosition = load_release_HatchPanel;
        return loadBall;
    }
    
}
