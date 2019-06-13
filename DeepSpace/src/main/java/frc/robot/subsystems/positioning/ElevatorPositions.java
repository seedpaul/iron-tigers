package frc.robot.subsystems.positioning;

public class ElevatorPositions{

    public static final int home = 0;
    public static int current = 0;

    private static final int hatchLevel1 = 2750; //1
    private static final int hatchLevelInBetween = 3150; //1
    private static final int hatchLevel2 = 11750; //3
    private static final int hatchLevel3 = 21000; //5

    // private static final int cargoLevel1 = 5250; //2
    // private static final int cargoLevel2 = 13550; //4
    // private static final int cargoLevel3 = 21000; //6

    //                          index:      0        1           2           3           4           5           6
    //public static final int[] positions = {home,hatchLevel1,cargoLevel1,hatchLevel2,cargoLevel2,hatchLevel3,cargoLevel3};
    public static final int[] positions = {home,hatchLevel1,hatchLevelInBetween,hatchLevel2,hatchLevel3};

    private static int currentIndex = 0;

    public static int getHighestPosition(){
        return positions[positions.length-1];
    }

    public static int getHomePosition(){
       return positions[0];
    }

    public static int down(){

        if(currentIndex > 0){
            currentIndex--;
        }
        current =  positions[currentIndex];
        return positions[currentIndex];
    }
    public static int up(){

        if(currentIndex < (positions.length-1)){
            currentIndex++;
        }
        current =  positions[currentIndex];
        return positions[currentIndex];
    }

    public static int downSimple(){

        if(current > 0){
            current--;
        }
        return current;
    }

    public static int upSimple(){

        if(currentIndex < (positions.length-1)){
            current++;
        }
        return current;
    }
}