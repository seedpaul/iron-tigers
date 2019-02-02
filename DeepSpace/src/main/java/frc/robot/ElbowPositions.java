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
public class ElbowPositions {
    
    private static int elbowIncrement = 100;
    private static int elbowMax = 18192;
    private static int elbowMin = 0;
    private static int currentPosition = 0;

    public static int getMax(){
        return elbowMax;
    }

    public static int getMin(){
        return elbowMin;
    }

    public static int up(){
        //move position up one increment
        if(currentPosition < (elbowMax-elbowIncrement)){
            currentPosition+=elbowIncrement;
          }
          return currentPosition;
    }

    public static int down(){
        //move down one increment
        if(currentPosition > (elbowMin+elbowIncrement)){
            currentPosition-=elbowIncrement;
          }

          return currentPosition;
    }
    
}
