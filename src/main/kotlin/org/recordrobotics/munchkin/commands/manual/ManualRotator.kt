package org.recordrobotics.munchkin.commands.manual

import edu.wpi.first.wpilibj2.command.CommandBase
import org.recordrobotics.munchkin.control.IControlInput
import org.recordrobotics.munchkin.subsystems.Rotator

/** Rotator teleop command */
class ManualRotator(rotator: Rotator?, control: IControlInput?) : CommandBase() {
	private val _rotator: Rotator
	private val _controls: IControlInput

	init {
		requireNotNull(rotator) { "Rotator is null" }
		requireNotNull(control) { "Control is null" }
		_rotator = rotator
		_controls = control
		addRequirements(_rotator)
	}

	override fun initialize() {
		_rotator.rotate(0.0)
	}

	override fun execute() {
		var speed = _controls.rotate
		if (Math.abs(speed) < MIN_SPEED) {
			speed = 0.0
		}
		_rotator.rotate(speed)
	}

	override fun end(isInterrupted: Boolean) {
		_rotator.rotate(0.0)
	}

	companion object {
		private const val MIN_SPEED = 0.15
	}
}
