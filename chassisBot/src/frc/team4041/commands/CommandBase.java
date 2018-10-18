package frc.team4041.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4041.OI;
import frc.team4041.subsystems.DriveTrain;


public abstract class CommandBase extends Command {

    public static DriveTrain drivetrain;
    private static OI oi;

    public CommandBase(){
        super();
    }

    @Override
    protected void initialize() {}

    public static void init(){
        drivetrain = DriveTrain.getInstance();

        oi = new OI();
        oi.init();
    }
}
