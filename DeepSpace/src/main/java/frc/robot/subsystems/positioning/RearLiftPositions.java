package frc.robot.subsystems.positioning;

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
