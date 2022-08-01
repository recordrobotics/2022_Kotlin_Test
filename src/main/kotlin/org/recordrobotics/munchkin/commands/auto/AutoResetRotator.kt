package org.recordrobotics.munchkin.commands.auto

import edu.wpi.first.wpilibj2.command.CommandBase
import org.recordrobotics.munchkin.subsystems.Rotator

/** Move lift towards the E-board limit switch Resets encoder at that point */
class AutoResetRotator(rotator: Rotator?, speed: Double) : CommandBase() {
	private val _rotator: Rotator
	private val _speed: Double

	init {
		require(speed > 0) { "Speed must be positive" }
		requireNotNull(rotator) { "Rotator is null" }
		_rotator = rotator
		_speed = speed
		addRequirements(rotator)
	}

	/** Move lift towards reset position */
	override fun initialize() {
		_rotator.rotate(_speed)
	}

	/** Command finished when limit switch hit */
	override fun isFinished(): Boolean {
		return _rotator.isFwdLimitPressed
	}

	/** Reset encoder and stop motor at the end */
	override fun end(interrupted: Boolean) {
		_rotator.rotate(0.0)
		_rotator.resetEncoders()
	}
}
