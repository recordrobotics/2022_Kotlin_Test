package org.recordrobotics.munchkin.control

import edu.wpi.first.wpilibj.XboxController
import org.recordrobotics.munchkin.control.IControlInput.FlywheelState

class LegacyControl(port: Int) : IControlInput {
	private val _gamepad: XboxController

	// Toggles for buttons - inversed when button is pressed
	private var _btnX = false
	private var _btnY = false

	init {
		_gamepad = XboxController(port)
	}

	override val driveLong: Double
		get() = _gamepad.leftY
	override val driveLat: Double
		get() = _gamepad.leftX
	override val rotate: Double
		get() = _gamepad.rightX
	override val climb: Double
		get() = _gamepad.rightY

	override val acqSpin: Double
		get() {
			// Forward mimics button-like behavior
			val forward = _gamepad.leftTriggerAxis > TRIGGER_THRESHOLD
			val backward = _gamepad.leftBumper

			// Forward takes precedence
			if (forward) return 1.0 else if (backward) return -1.0
			return 0.0
		}

	override val acqTilt: Double
		get() {
			// Out mimics button-like behavior
			val out = _gamepad.rightTriggerAxis > TRIGGER_THRESHOLD
			val `in` = _gamepad.rightBumper

			// Out takes precedence
			if (out) return 1.0 else if (`in`) return -1.0
			return 0.0
		}
	// We still want to check Y, to reset it

	// If X not pressed, clear Y

	// Otherwise Y determines the state
	override val flywheel: FlywheelState
		get() {
			if (_gamepad.xButtonPressed) _btnX = !_btnX
			// We still want to check Y, to reset it
			if (_gamepad.yButtonPressed) _btnY = !_btnY

			// If X not pressed, clear Y
			if (!_btnX) {
				_btnY = false
				return FlywheelState.OFF
			}

			// Otherwise Y determines the state
			return if (_btnY) FlywheelState.HIGH else FlywheelState.LOW
		}

	override val servos: Boolean
		get() = _gamepad.aButton

	override fun toString(): String {
		return "Legacy"
	}

	companion object {
		private const val TRIGGER_THRESHOLD = 0.25
	}
}
