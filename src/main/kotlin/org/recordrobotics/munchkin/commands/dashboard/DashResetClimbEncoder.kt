package org.recordrobotics.munchkin.commands.dashboard

import edu.wpi.first.wpilibj2.command.CommandBase
import org.recordrobotics.munchkin.subsystems.Climbers

class DashResetClimbEncoder(climbers: Climbers?) : CommandBase() {
	private val _climbers: Climbers

	init {
		requireNotNull(climbers) { "Climbers is null" }
		_climbers = climbers
	}

	/** Reset climber encoder values when started */
	override fun initialize() {
		_climbers.resetEncoderValue()
	}

	/** Command finished immediately */
	override fun isFinished(): Boolean {
		return true
	}
}
