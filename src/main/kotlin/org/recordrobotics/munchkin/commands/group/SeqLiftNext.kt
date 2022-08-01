package org.recordrobotics.munchkin.commands.group

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup
import org.recordrobotics.munchkin.commands.auto.AutoClimbTo
import org.recordrobotics.munchkin.commands.auto.AutoPullUp
import org.recordrobotics.munchkin.commands.auto.AutoResetRotator
import org.recordrobotics.munchkin.commands.auto.AutoRotateTo
import org.recordrobotics.munchkin.subsystems.Climbers
import org.recordrobotics.munchkin.subsystems.Rotator

/**
 * Climb robot to from one bar next bar Setup:
 * - Robot is hanging from one bar by the hooks
 * - Hooks are fully retracted (encoder 0) Expected outcome:
 * - Robot will get onto the next bar
 */
class SeqLiftNext(rotator: Rotator?, climbers: Climbers?) : SequentialCommandGroup() {
	init {
		addCommands( // Hang robot using rotator tower
			AutoRotateTo(rotator, ROTATOR_BAR_TARGET, ROTATOR_SPEED), // Push climbers off bar
			AutoClimbTo(climbers, CLIMBERS_BAR_TARGET, CLIMBERS_SPEED), // Extend to next bar
			ParallelCommandGroup(
				AutoRotateTo(rotator, ROTATOR_EXTEND_TARGET, ROTATOR_SPEED),
				AutoClimbTo(climbers, CLIMBERS_EXTEND_TARGET, CLIMBERS_SPEED)
			), // Align hooks with bar
			AutoRotateTo(rotator, ROTATOR_PULL_TARGET, ROTATOR_SPEED), // Lift robot from lower bar
			AutoClimbTo(climbers, CLIMBERS_PULL_TARGET, CLIMBERS_SPEED), // Lift to next bar
			ParallelCommandGroup(
				AutoResetRotator(rotator, ROTATOR_SPEED), AutoPullUp(climbers, CLIMBERS_SPEED)
			)
		)
	}

	companion object {
		private const val ROTATOR_SPEED = 0.5
		private const val CLIMBERS_SPEED = 0.5
		private const val ROTATOR_BAR_TARGET = -42.0
		private const val ROTATOR_EXTEND_TARGET = -96.0
		private const val ROTATOR_PULL_TARGET = -83.0
		private const val CLIMBERS_BAR_TARGET = -60.0
		private const val CLIMBERS_EXTEND_TARGET = -410.0
		private const val CLIMBERS_PULL_TARGET = -250.0
	}
}
