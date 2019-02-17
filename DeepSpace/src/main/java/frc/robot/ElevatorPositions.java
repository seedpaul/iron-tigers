package frc.robot;

public class ElevatorPositions{

    public static final int home = 0;

    private static final int hatchLevel1 = 3000;
    private static final int hatchLevel2 = 9000;
    private static final int hatchLevel3 = 15000;

    private static final int cargoLevel1 = 1500;
    private static final int cargoLevel2 = 6000;
    private static final int cargoLevel3 = 12000;

    public static final int[] Position = {home,cargoLevel1,hatchLevel1,cargoLevel2,hatchLevel2,cargoLevel3,hatchLevel3};

    public static int getHighestPosition(){
    return Position[Position.length-1];
    }

    public static int getHomePosition(){
    return Position[0];
    }

}