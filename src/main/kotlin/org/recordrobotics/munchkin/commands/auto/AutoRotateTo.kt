package org.recordrobotics.munchkin.commands.auto

import edu.wpi.first.wpilibj2.command.CommandBase
import org.recordrobotics.munchkin.subsystems.Rotator

/** Rotate to encoder value target */
class AutoRotateTo(rotator: Rotator?, target: Double, speed: Double) : CommandBase() {
	private val _rotator: Rotator
	private val _speed: Double
	private val _target: Double
	private var _direction: Direction? = null

	init {
		require(speed > 0) { "Speed must be positive" }
		requireNotNull(rotator) { "Rotator is null" }
		_rotator = rotator
		_speed = speed
		_target = target
		addRequirements(rotator)
	}

	/** Rotate lift towards target */
	override fun initialize() {
		// Calculate direction
		val dx = _target - _rotator.position
		_direction = if (dx > 0) Direction.FORWARD else Direction.BACKWARD
		_rotator.rotate(_speed * _direction!!.value())
	}

	/** Finished when target is reached or passed */
	override fun isFinished(): Boolean {
		return if (_direction == Direction.FORWARD) {
			_rotator.position >= _target
		} else {
			_rotator.position <= _target
		}
	}

	/** Reset motors when ending */
	override fun end(interrupted: Boolean) {
		_rotator.rotate(0.0)
	}
}
