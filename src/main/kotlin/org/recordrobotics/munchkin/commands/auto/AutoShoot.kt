package org.recordrobotics.munchkin.commands.auto

import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj2.command.CommandBase
import org.recordrobotics.munchkin.subsystems.Flywheel

class AutoShoot(flywheel: Flywheel?, speed: Double) : CommandBase() {
	private val _flywheel: Flywheel
	private val _speed: Double
	private val _shootTimer = Timer()

	init {
		require(speed > 0) { "Speed must be positive" }
		requireNotNull(flywheel) { "Flywheel is null" }
		_speed = speed
		_flywheel = flywheel
		addRequirements(flywheel)
	}

	override fun initialize() {
		_shootTimer.start()
		_flywheel.spin(_speed)
	}

	/** Shoot after delay */
	override fun execute() {
		if (_shootTimer.get() > SHOOT_DELAY) {
			_flywheel.shootServos()
		}
	}

	/** Command ends when time runs out */
	override fun isFinished(): Boolean {
		return _shootTimer.get() >= END_TIME
	}

	/** Stops the flywheel and resets the servos once finished */
	override fun end(interrupted: Boolean) {
		_flywheel.spin(0.0)
		_flywheel.resetServos()
		_shootTimer.stop()
		_shootTimer.reset()
	}

	companion object {
		// Delay to allow the flywheel to spin up
		private const val SHOOT_DELAY = 0.8

		// Time needed to execute the command
		private const val END_TIME = 2.0
	}
}
