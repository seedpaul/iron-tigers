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
    
    private static int currentPosition = 0;
    private static int tightest = 100;
    private static int widest = 18192;
    private static int loadHatchPanel = 0;
    private static int releaseHatchPanel = 0;
    private static int holdHatchPanel = 0;
    private static int clampBall = 0;
    private static int releaseBall = 0;
    private static int pickUpBall = 0;
    private static int clawIntakeIncrement = 100;



    public static int getMax(){
        return elbowMax;
    }

    public static int getMin(){
        return elbowMin;
    }

    public static int up(){
        //move position up one increment
        if(currentPosition < (elbowMax-clawIntakeIncrement)){
            currentPosition+=clawIntakeIncrement;
          }
          return currentPosition;
    }

    public static int down(){
        //move down one increment
        if(currentPosition > (elbowMin+clawIntakeIncrement)){
            currentPosition-=clawIntakeIncrement;
          }

          return currentPosition;
    }
    
}
