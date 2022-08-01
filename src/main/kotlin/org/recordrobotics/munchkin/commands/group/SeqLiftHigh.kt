package org.recordrobotics.munchkin.commands.group

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup
import org.recordrobotics.munchkin.subsystems.Climbers
import org.recordrobotics.munchkin.subsystems.Rotator

/**
 * Climb robot from ground to high bar Setup:
 * - Climbers extended and placed directly above middle bar Expected outcome:
 * - Robot will hang from high bar with climbers
 */
class SeqLiftHigh(rotator: Rotator?, climbers: Climbers?) : SequentialCommandGroup() {
	init {
		addCommands(SeqLiftMid(rotator, climbers), SeqLiftNext(rotator, climbers))
	}
}
