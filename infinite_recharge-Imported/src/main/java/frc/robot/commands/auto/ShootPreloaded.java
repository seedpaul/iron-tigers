/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Bombardier;

public class ShootPreloaded extends CommandBase {

  private final Bombardier m_Bombardier;
  private boolean done = false;
  private int ballCount = 1;


  public ShootPreloaded(Bombardier in_Bombardier) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Bombardier = in_Bombardier;
    addRequirements(m_Bombardier);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //let indexer know about preloaded ball
    m_Bombardier.setIndexerBallCount(ballCount);
 
  }

  // Called every time the schedMuler runs while the command is scheduled.
  @Override
  public void execute() {

    //enable targeting and disable auto indexing
    m_Bombardier.target(true, false);

    // function is finished when we've added the two balls 
    // stored in the hopper
    
    if( m_Bombardier.getIndexerBallCount() >= 2){
      Timer.delay(0.45);
      done = true;
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
    //disable targeting and enable auto indexing
    m_Bombardier.target(false,true);

    //reset indexer
    m_Bombardier.resetIndexer();
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
