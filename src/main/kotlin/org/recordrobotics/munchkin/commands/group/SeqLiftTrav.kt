package org.recordrobotics.munchkin.commands.group

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup
import org.recordrobotics.munchkin.subsystems.Climbers
import org.recordrobotics.munchkin.subsystems.Rotator

/**
 * Climb robot from ground to traversal bar Setup:
 * - Climbers extended and placed directly above middle bar Expected outcome:
 * - Robot will hang from traversal bar with climbers
 */
class SeqLiftTrav(rotator: Rotator?, climbers: Climbers?) : SequentialCommandGroup() {
	init {
		addCommands(
			SeqLiftMid(rotator, climbers),
			SeqLiftNext(rotator, climbers),
			SeqLiftNext(rotator, climbers) // todo: maybe raise acq?
		)
	}
}
