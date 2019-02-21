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

    public static final int rear_home = 0;

    public static final int rear_habLevel6 = -4500;
    public static final int rear_habLevel19 = -10250;

    public static int getHighestPosition(){
        return rear_habLevel19;
    }

    public static int getHomePosition(){
        return rear_home;
    }
}
