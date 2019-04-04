package frc.robot.subsystems.positioning;

public class IntakeElbowPositions {
    
    
    private static int pulsesPerRevolutionAtOutPointShaft = 3402;//4200;
    private static double ratio = pulsesPerRevolutionAtOutPointShaft/360;
    private static int finalElbowGearRatio = 66/18;
    public static int home_position = 0;//this is up

    private static int eject_position = (int)(ratio * 2); //45 degrees
    private static int hatch_position = (int)(ratio * 4);//90 degrees
    private static int intake_Position = (int)(ratio * 6); //100 degrees

    // private static int eject_position = (int)(ratio * 45); //45 degrees
    // private static int hatch_position = (int)(ratio * 90);//90 degrees
    // private static int intake_Position = (int)(ratio * 100); //100 degrees
    private static int currentIndex = 3;
 
                            // index         0               1               2             3
    private static int[] positions = {intake_Position,hatch_position,eject_position,home_position};

    public static int getMax(){
        return home_position;
    }

    public static int getMin(){
        return intake_Position;
    }

    public static int intake(){
        //move down one increment
        return intake_Position;
    }

    public static int eject(){
        return eject_position;
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
