package frc.robot.subsystems.positioning;

public class IntakeElbowPositions {
    
    
    public static int home_position = 20000;//this is up
    private static int eject_position = 18000;
    private static int hatch_position = 17000;
    private static int intake_Position = 16000;//this is down
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
