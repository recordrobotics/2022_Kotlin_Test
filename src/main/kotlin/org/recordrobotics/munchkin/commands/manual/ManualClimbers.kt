package org.recordrobotics.munchkin.commands.manual

import edu.wpi.first.wpilibj2.command.CommandBase
import org.recordrobotics.munchkin.control.IControlInput
import org.recordrobotics.munchkin.subsystems.Climbers

/** Climber teleop command */
class ManualClimbers(climber: Climbers?, controlInput: IControlInput?) : CommandBase() {
	private val _climbers: Climbers
	private val _controls: IControlInput

	init {
		requireNotNull(climber) { "Climber is null" }
		requireNotNull(controlInput) { "Control is null" }
		_climbers = climber
		_controls = controlInput
		addRequirements(_climbers)
	}

	override fun execute() {
		var speed = _controls.climb
		if (Math.abs(speed) < MIN_SPEED) {
			speed = 0.0
		}
		_climbers.move(speed)
	}

	override fun end(interrupted: Boolean) {
		_climbers.move(0.0)
	}

	companion object {
		private const val MIN_SPEED = 0.10
	}
}
