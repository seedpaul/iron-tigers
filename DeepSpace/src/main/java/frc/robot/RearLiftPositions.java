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
public class RearLiftPositions {

    public static final int home = 0;

    public static final int habLevel6 = 6;
    public static final int habLevel19 = 9;

    public static final int[] Position = {home, habLevel6, habLevel19};

    public static int getHighestPosition(){
    return Position[Position.length-1];
    }

    public static int getHomePosition(){
    return Position[0];
    }
}
