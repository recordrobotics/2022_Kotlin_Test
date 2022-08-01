package org.recordrobotics.munchkin.commands.manual

import edu.wpi.first.wpilibj2.command.CommandBase
import org.recordrobotics.munchkin.control.IControlInput
import org.recordrobotics.munchkin.subsystems.Acquisition

/** Acquisition teleop command */
class ManualAcquisition(acquisition: Acquisition?, controlInput: IControlInput?) : CommandBase() {
	private val _acquisition: Acquisition
	private val _controls: IControlInput

	init {
		requireNotNull(acquisition) { "Acquisition is null" }
		requireNotNull(controlInput) { "Control is null" }
		_acquisition = acquisition
		_controls = controlInput
		addRequirements(_acquisition)
	}

	override fun execute() {
		// spin controls (if = intake balls, else if = expel balls,  else = stop motor)
		if (_controls.acqSpin > 0) {
			_acquisition.spin(SPIN_SPEED)
		} else if (_controls.acqSpin < 0) {
			_acquisition.spin(-SPIN_SPEED)
		} else {
			_acquisition.spin(0.0)
		}

		// tilt controls (if = tilt down, else if = tilt up,  else = stop motor)
		if (_controls.acqTilt > 0 && !_acquisition.tiltState) {
			_acquisition.tilt(TILT_SPEED)
		} else if (_controls.acqTilt < 0) {
			_acquisition.tilt(-TILT_SPEED)
		} else {
			_acquisition.tilt(0.0)
		}
	}

	override fun end(interrupted: Boolean) {
		_acquisition.tilt(0.0)
		_acquisition.spin(0.0)
	}

	companion object {
		private const val SPIN_SPEED = -0.5
		private const val TILT_SPEED = -0.5
	}
}
