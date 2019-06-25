package frc.robot.subsystems.positioning;

public class IntakeElbowPositions {

    public static int home_position = 0;//this is up
    private static int eject_position = -30;
    private static int hatch_position = -65;
    private static int intake_Position = -90;

    private static int currentIndex = 3;
 
                            // index         0               1               2             3
    private static int[] positions = {intake_Position,hatch_position,eject_position,home_position};

    public static int getHigh(){
        return home_position;
    }

    public static int getLow(){
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

        if(currentIndex < 3){
            currentIndex++;
        }
        return positions[currentIndex];
    }

}
