package org.recordrobotics.munchkin.commands.group

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup
import org.recordrobotics.munchkin.commands.auto.AutoPullUp
import org.recordrobotics.munchkin.commands.auto.AutoResetRotator
import org.recordrobotics.munchkin.subsystems.Climbers
import org.recordrobotics.munchkin.subsystems.Rotator

/**
 * Climb robot from ground to middle bar Setup:
 * - Climbers extended and placed directly above bar Expected outcome:
 * - Robot will hang from middle bar with climbers
 */
class SeqLiftMid(rotator: Rotator?, climbers: Climbers?) : SequentialCommandGroup() {
	init {
		addCommands( // todo: drop acq
			// Lift to mid
			ParallelCommandGroup(
				AutoResetRotator(rotator, ROTATOR_SPEED), AutoPullUp(climbers, CLIMBERS_SPEED)
			)
		)
	}

	companion object {
		private const val ROTATOR_SPEED = 0.5
		private const val CLIMBERS_SPEED = 0.5
	}
}
