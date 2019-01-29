package frc.robot;

public class ElevatorPositions{

    private static int baseIncrement = 18192;

    private static final int home = 0;

    private static final int hatchLevel1 = baseIncrement*2;
    private static final int hatchLevel2 = baseIncrement*4;
    private static final int hatchLevel3 = baseIncrement*6;

    private static final int cargoLevel1 = baseIncrement*1;
    private static final int cargoLevel2 = baseIncrement*3;
    private static final int cargoLevel3 = baseIncrement*5;

    public static final int[] Position = {home,cargoLevel1,hatchLevel1,cargoLevel2,hatchLevel2,cargoLevel3,hatchLevel3};

    public static int getHighestPosition(){
    return Position[Position.length-1];
    }

    public static int getHomePosition(){
    return Position[0];
    }

}