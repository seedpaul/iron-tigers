package frc.team4041.subsystems;//package frc.team4041.subsystems;
//
//import edu.wpi.first.wpilibj.AnalogInput;
//import edu.wpi.first.wpilibj.command.Subsystem;
//
//public class RangeFinder extends Subsystem {
//
//    // ToDo: put range finder Digital IO port in robot map
//													//ping,echo
//	private static final AnalogInput sensor = new AnalogInput(0);
//	
//	private static RangeFinder instance;
//	
//	private static double scalingFactor = 0.00096758;
//
//	public RangeFinder() {
//		initialize();
//	}
//
//	public static RangeFinder getInstance() {
//		if (instance == null) {
//			instance = new RangeFinder();
//		}
//		return instance;
//	}
//	
//	private void initialize() {
//
//	}
//
//    public void initDefaultCommand() {
//        // Set the default command for a subsystem here.
//        //setDefaultCommand(new MySpecialCommand());
//    }
//    
//    public double getSensorDistance() {
//    	return sensor.getAverageVoltage()/scalingFactor;
//    }
//    
//}
//
