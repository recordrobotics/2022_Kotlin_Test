package org.recordrobotics.munchkin.commands.manual

import edu.wpi.first.wpilibj2.command.CommandBase
import org.recordrobotics.munchkin.control.IControlInput
import org.recordrobotics.munchkin.control.IControlInput.FlywheelState
import org.recordrobotics.munchkin.subsystems.Flywheel

/** Flywheel teleop command */
class ManualFlywheel(flywheel: Flywheel?, controlInput: IControlInput?) : CommandBase() {
	private val _flywheel: Flywheel
	private val _controls: IControlInput

	// used to reset servos
	private var _servosUp = false

	init {
		requireNotNull(flywheel) { "Flywheel is null" }
		requireNotNull(controlInput) { "Control is null" }
		_flywheel = flywheel
		_controls = controlInput
		addRequirements(_flywheel)
	}

	override fun execute() {
		when (_controls.flywheel) {
			FlywheelState.OFF -> {
				_flywheel.spin(IDLE_SPEED)
				_flywheel.resetServos()
				return
			}
			FlywheelState.LOW -> _flywheel.spin(LOW_SPEED)
			FlywheelState.HIGH -> _flywheel.spin(HIGH_SPEED)
		}
		if (_controls.servos) {
			_flywheel.shootServos()
			_servosUp = true
		} else if (_servosUp) {
			_flywheel.resetServos()
			_servosUp = false
		}
	}

	override fun end(interrupted: Boolean) {
		_flywheel.spin(0.0)
		_flywheel.resetServos()
	}

	companion object {
		// TODO: implement dashboard once we make it (it was there in the old code)
		private const val HIGH_SPEED = 0.35
		private const val LOW_SPEED = 0.22
		private const val IDLE_SPEED = 0.0
	}
}
