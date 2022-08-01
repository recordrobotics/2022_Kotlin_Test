package org.recordrobotics.munchkin.commands.auto

import edu.wpi.first.wpilibj2.command.CommandBase
import org.recordrobotics.munchkin.subsystems.Climbers

/** Pull up the climbers completely 0 is assumed to be fully retracted */
class AutoPullUp(climbers: Climbers?, speed: Double) : CommandBase() {
	private val _climbers: Climbers
	private val _speed: Double

	init {
		require(speed > 0) { "Speed must be positive" }
		requireNotNull(climbers) { "Climber is null" }
		_climbers = climbers
		_speed = speed
		addRequirements(climbers)
	}

	/** Pull climbers up */
	override fun initialize() {
		_climbers.move(_speed)
	}

	/** Finished when encoder value reaches zero */
	override fun isFinished(): Boolean {
		return _climbers.encoderValue >= 0
	}

	/** Stop climbers */
	override fun end(interrupted: Boolean) {
		_climbers.stop()
	}
}
