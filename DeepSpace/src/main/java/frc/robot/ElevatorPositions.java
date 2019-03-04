package frc.robot;

public class ElevatorPositions{

    public static final int home = 0;

    private static final int hatchLevel1 = 2000; //1
    private static final int hatchLevel2 = 10000; //3
    private static final int hatchLevel3 = 17000; //5

    private static final int cargoLevel1 = 5000; //2
    private static final int cargoLevel2 = 12000; //4
    private static final int cargoLevel3 = 19000; //6

    public static final int[] Position = {home,hatchLevel1,cargoLevel1,hatchLevel2,cargoLevel2,hatchLevel3,cargoLevel3};

    public static int getHighestPosition(){
        return Position[Position.length-1];
    }

    public static int getHomePosition(){
       return Position[0];
    }

    public static int getspeedLimitThreshold(){
        return cargoLevel2;
    }

}