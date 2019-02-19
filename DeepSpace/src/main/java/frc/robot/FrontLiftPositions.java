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

    public static final int front_home = 150000;
    public static final int front_habLevel19 = 100000;
    public static final int front_habLevel6 = 60000;

    public static final int front_habClimbStep1 = 37500;
    public static final int front_habClimbStep2 = 27500;

    public static int geLowestPosition(){
        return front_habClimbStep2;
    }

    public static int getHomePosition(){
        return front_home;
    }
}
