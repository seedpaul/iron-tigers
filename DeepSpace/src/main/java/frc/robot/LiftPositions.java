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
public class LiftPositions {
    private static int habIncrement = 18192;

    private static final int home = 0;

    private static final int habLevel6 = habIncrement*2;
    private static final int habLevel19 = habIncrement*4;
    private static final int habLevelClimbUp = habIncrement*6;

    public static final int[] Position = {home, habLevel6, habLevel19, habLevelClimbUp};

    public static int getHighestPosition(){
    return Position[Position.length-1];
    }

    public static int getHomePosition(){
    return Position[0];
    }
}
