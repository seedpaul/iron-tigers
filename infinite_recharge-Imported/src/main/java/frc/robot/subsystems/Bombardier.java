/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.components.*;

public class Bombardier extends SubsystemBase {
  /**
   * Creates a new Bombardier. This is the targeting and firing controller
   * it coordinates the related aiming and firing subsystems
   * Indexer,Turret,RangeSensors, and shooter during match
   * A bombardier is a member of a bomber crew in the US Air Force responsible 
   *  for sighting and releasing bombs.
   */
  private final Indexer m_Indexer;
  private final Turret m_Turret;
  private final Shooter m_Shooter;
  private final LimeLight m_LimeLight;
  //private final IntakeWheels m_IntakeWheels;

  private boolean activateTargeting = false;

  public Bombardier(Indexer in_Indexer, Turret in_Turret, Shooter in_Shooter, LimeLight in_LimeLight, IntakeWheels in_IntakeWheels) {
    m_Indexer = in_Indexer;
    m_Turret = in_Turret;
    m_Shooter = in_Shooter;
    m_LimeLight = in_LimeLight;
    //m_IntakeWheels = in_IntakeWheels;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(activateTargeting){
     this.doTargeting();
    }else{
      this.noTargeting();
    }
  }

  public void target(boolean aquire, boolean enabledAutoIndexing){
    //this function enables/disabled turret targeting
    if(aquire){
      activateTargeting = true;
    }else{
      activateTargeting = false;
    }
    m_Indexer.enabledAutoIndexing(enabledAutoIndexing);
  }

  public void setIndexerBallCount(int in_ballCount){
    m_Indexer.setBallCount(in_ballCount);
  }

  public int getIndexerBallCount(){
    return m_Indexer.getBallCount();
  }

  public void resetIndexer(){
    m_Indexer.reset();
  }

 // internal processing functions *************************************************
  private void doTargeting(){
    m_LimeLight.ledOn();
    m_Turret.targetingEnabled(m_LimeLight.getXOffset());
    if(m_Turret.onTarget() && m_LimeLight.hasValidTarget()){
      m_Shooter.on(m_LimeLight.getDistanceToTarget());
      if(m_Shooter.readyToShoot()){   
        m_Indexer.onShoot();
        //m_IntakeWheels.on();
      }
    }
  }

  private void noTargeting(){
    m_LimeLight.ledOff();
    m_Turret.targetingDisabled();
    m_Shooter.off();
  }
}
