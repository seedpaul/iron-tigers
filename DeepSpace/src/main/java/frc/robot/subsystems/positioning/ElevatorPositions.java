package frc.robot.subsystems.positioning;

public class ElevatorPositions{

    public static final int home = 0;

    private static final int hatchLevel1 = 2000; //1
    private static final int hatchLevel2 = 10000; //3
    private static final int hatchLevel3 = 17000; //5

    private static final int cargoLevel1 = 5000; //2
    private static final int cargoLevel2 = 12000; //4
    private static final int cargoLevel3 = 19000; //6

    //                          index:      0        1           2           3           4           5           6
    public static final int[] positions = {home,hatchLevel1,cargoLevel1,hatchLevel2,cargoLevel2,hatchLevel3,cargoLevel3};

    private static int currentIndex = 0;

    public static int getHighestPosition(){
        return positions[positions.length-1];
    }

    public static int getHomePosition(){
       return positions[0];
    }

    public static int getspeedLimitThreshold(){
        return cargoLevel2;
    }

    public static int down(){

        if(currentIndex > 0){
            currentIndex--;
        }
        return positions[currentIndex];
    }
    public static int up(){

        if(currentIndex < (positions.length-1)){
            currentIndex++;
        }
        return positions[currentIndex];
    }

}