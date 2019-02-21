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
    
    private static int eject_position = 5000;
    private static int home_position = 8200;//this is down
    private static int intake_Position = 4100;//this is up
    private static int currentIndex = 2;

    private static int[] positions = {intake_Position,eject_position,home_position};

    public static int getMax(){
        return home_position;
    }

    public static int getMin(){
        return intake_Position;
    }

    public static int home(){
        //move position up one increment
        return home_position;
    }

    public static int intake(){
        //move down one increment
        return intake_Position;
    }

    public static int eject(){
        return eject_position;
    }
    
    public static int down(){

        if(currentIndex > 0){
            currentIndex--;
        }
        return positions[currentIndex];
    }
    public static int up(){

        if(currentIndex < 3){
            currentIndex++;
        }
        return positions[currentIndex];
    }

}
