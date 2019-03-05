package frc.robot.subsystems.positioning;

public class FrontLiftPositions {

    private static final int front_home = 150000;
    public static final int front_habLevel19 = 100000;
    public static final int front_habLevel6 = 60000;

    public static final int front_habClimbStep1 = 37500;
    public static final int front_habClimbStep2 = 17500;

    public static int geLowestPosition(){
        return front_habClimbStep2;
    }

    public static int getHomePosition(){
        return front_home;
    }
}
