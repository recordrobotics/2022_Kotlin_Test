package org.recordrobotics.munchkin.commands.auto

import edu.wpi.first.wpilibj2.command.CommandBase
import org.recordrobotics.munchkin.subsystems.Acquisition

class AutoDeployAcq(acquisition: Acquisition?, speed: Double) : CommandBase() {
	private val _acquisition: Acquisition
	private val _speed: Double

	init {
		require(speed > 0) { "Speed must be positive" }
		requireNotNull(acquisition) { "Acquisition is null" }
		// setting _speed to -speed so the aquisition tilts down
		_speed = -speed
		_acquisition = acquisition
		addRequirements(acquisition)
	}

	/** tilt acquisition down */
	override fun initialize() {
		_acquisition.tilt(_speed)
	}

	/** command ends when limit switch is hit */
	override fun isFinished(): Boolean {
		return _acquisition.tiltState
	}

	/** stop motor once finished */
	override fun end(interrupted: Boolean) {
		_acquisition.tilt(0.0)
	}
}
