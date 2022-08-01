package org.recordrobotics.munchkin.commands.auto

import edu.wpi.first.wpilibj2.command.CommandBase
import org.recordrobotics.munchkin.subsystems.Climbers

class AutoClimbTo(climbers: Climbers?, target: Double, speed: Double) : CommandBase() {
	private val _climbers: Climbers
	private val _speed: Double
	private val _target: Double
	private var _direction: Direction? = null

	init {
		require(speed > 0) { "Speed must be positive" }
		requireNotNull(climbers) { "Climbers is null" }
		_climbers = climbers
		_target = target
		_speed = speed
		addRequirements(climbers)
	}

	override fun initialize() {
		// Calculate direction
		val dx = _target - _climbers.encoderValue
		_direction = if (dx > 0) Direction.DOWN else Direction.UP
		_climbers.move(_speed * _direction!!.value())
	}

	override fun isFinished(): Boolean {
		return if (_direction == Direction.DOWN) {
			_climbers.encoderValue >= _target
		} else {
			_climbers.encoderValue <= _target
		}
	}

	override fun end(interrupted: Boolean) {
		_climbers.move(0.0)
	}
}
