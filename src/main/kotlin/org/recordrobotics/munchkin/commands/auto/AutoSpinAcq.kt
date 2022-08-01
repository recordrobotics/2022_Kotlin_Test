package org.recordrobotics.munchkin.commands.auto

import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj2.command.CommandBase
import org.recordrobotics.munchkin.subsystems.Acquisition

class AutoSpinAcq(acquisition: Acquisition?, speed: Double, collectTime: Double) : CommandBase() {
	private val _acquisition: Acquisition
	private val _speed: Double
	private val _collectTimer = Timer()
	private val _collectTime: Double

	init {
		require(speed > 0) { "Speed must be positive" }
		requireNotNull(acquisition) { "Acquisition is null" }
		require(collectTime >= 0) { "Collect Time must be positive" }
		// setting _speed to -speed so the aquisition intakes balls
		_speed = -speed
		_collectTime = collectTime
		_acquisition = acquisition
		addRequirements(acquisition)
	}

	/** spin acquisition down */
	override fun initialize() {
		_acquisition.spin(_speed)
		_collectTimer.start()
	}

	/** command ends when a set amt of time passes */
	override fun isFinished(): Boolean {
		return _collectTimer.get() > _collectTime
	}

	/** stop motor once finished */
	override fun end(interrupted: Boolean) {
		_acquisition.spin(0.0)
		_collectTimer.stop()
		_collectTimer.reset()
	}
}
