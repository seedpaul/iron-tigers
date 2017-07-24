package org.usfirst.frc.team4041.robot.commandGroups;

import org.usfirst.frc.team4041.robot.commands.GrabWithControllerDown;
import org.usfirst.frc.team4041.robot.commands.PickupForGroup;


import edu.wpi.first.wpilibj.command.CommandGroup;

public class GrabAndPickUpWithController extends CommandGroup {

    public GrabAndPickUpWithController() {
    		addParallel(new PickupForGroup(), 60);
        	addParallel(new GrabWithControllerDown(), 60);
    }
}
