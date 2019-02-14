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
public class FrontLiftPositions {

    public static final int home = 150000;

    public static final int habLevel6 = 50000;
    public static final int habLevel19 = 100000;
    public static final int habClimb = 0;

    public static int geLowestPosition(){
        return habClimb;
    }

    public static int getHomePosition(){
        return home;
    }
}
