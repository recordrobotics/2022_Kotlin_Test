package org.recordrobotics.munchkin.control

import edu.wpi.first.wpilibj.XboxController
import org.recordrobotics.munchkin.control.IControlInput.FlywheelState

class DoubleControl(port1: Int, port2: Int) : IControlInput {
	private val _gamepad1: XboxController
	private val _gamepad2: XboxController

	// Toggles for buttons on G2 - inversed when button is pressed
	private var _btnX = false
	private var _btnY = false

	init {
		_gamepad1 = XboxController(port1)
		_gamepad2 = XboxController(port2)
	}

	override val driveLong: Double
		get() = _gamepad1.leftY
	override val driveLat: Double
		get() = _gamepad1.leftX
	override val rotate: Double
		get() = _gamepad2.rightX
	override val climb: Double
		get() = _gamepad2.leftY

	// Forward mimics button-like behavior
	override val acqSpin:

		// Forward takes precedence
		Double
			get() {
				// Forward mimics button-like behavior
				val forward =
					(
						_gamepad1.leftTriggerAxis > TRIGGER_THRESHOLD ||
							_gamepad1.rightTriggerAxis > TRIGGER_THRESHOLD
						)
				val backward = _gamepad1.leftBumper || _gamepad1.rightBumper

				// Forward takes precedence
				if (forward) return 1.0 else if (backward) return -1.0
				return 0.0
			}

	// Out mimics button-like behavior
	override val acqTilt:

		// Out takes precedence
		Double
			get() {
				// Out mimics button-like behavior
				val out =
					(
						_gamepad2.leftTriggerAxis > TRIGGER_THRESHOLD ||
							_gamepad2.rightTriggerAxis > TRIGGER_THRESHOLD
						)
				val `in` = _gamepad2.leftBumper || _gamepad2.rightBumper

				// Out takes precedence
				if (out) return 1.0 else if (`in`) return -1.0
				return 0.0
			}
	// We still want to check Y, to reset it

	// If X not pressed, clear Y

	// Otherwise Y determines the state
	override val flywheel: FlywheelState
		get() {
			if (_gamepad2.xButtonPressed) _btnX = !_btnX
			// We still want to check Y, to reset it
			if (_gamepad2.yButtonPressed) _btnY = !_btnY

			// If X not pressed, clear Y
			if (!_btnX) {
				_btnY = false
				return FlywheelState.OFF
			}

			// Otherwise Y determines the state
			return if (_btnY) FlywheelState.HIGH else FlywheelState.LOW
		}
	override val servos: Boolean
		get() = _gamepad1.aButton

	override fun toString(): String {
		return "Double"
	}

	companion object {
		private const val TRIGGER_THRESHOLD = 0.25
	}
}
