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
    
    
    private static int load_release_HatchPanel = 0;//fully closed 19421
    private static int holdHatchPanel = -4000;
    private static int clampBall = -9000;

    // releaseBall is a special position that will be skipped by normal open and close function
    // it will be tied together with flipper extend to eject the ball with one button push
    private static int releaseBall = -12000;

    private static int loadBall = -22000;//fully open
    private static int currentIndex = 0;

                            // index         0               1               2           3             4
    private static int[] positions = {load_release_HatchPanel,holdHatchPanel,clampBall,releaseBall,loadBall};

    public static int getMax(){
        return load_release_HatchPanel;
    }

    public static int getMin(){
        return loadBall;
    }

    public static int releaseBall(){
        return releaseBall;
    }
    
    public static int close(){

        if(currentIndex > 0){
            currentIndex--;
        }
        return positions[currentIndex];
    }
    public static int open(){

        if(currentIndex < (positions.length-1)){
            currentIndex++;
        }
        return positions[currentIndex];
    }
    
}
