package frc.team4041.commands;

import frc.team4041.OI;

public class ArcadeDrive extends CommandBase {
    public ArcadeDrive() {
        requires(drivetrain);
    }

    @Override
    protected void initialize() {}

    @Override
    protected void execute() {
        drivetrain.arcadeDrive(OI.getDriverStick(),1,4);
        drivetrain.dataForDashboard();
    }

    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    protected void end() {}

    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
