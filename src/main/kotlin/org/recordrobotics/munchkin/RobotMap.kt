package org.recordrobotics.munchkin

/** Hardware ports for computer and robot */
object RobotMap {
	/** Acquisition ports */
	object Acquisition {
		// DIO
		const val LIMIT_SWITCH = 2

		// CAN
		const val SPIN_MOTOR_PORT = 7
		const val BALL_CHANNEL_MOTOR_PORT = 8
		const val TILT_MOTOR_PORT = 9
	}

	/** Climber ports (CAN) */
	object Climbers {
		const val LEFT_MOTOR_PORT = 10
		const val RIGHT_MOTOR_PORT = 6
	}

	/** Control ports (PC USB) */
	object Control {
		// LegacyControl
		const val LEGACY_GAMEPAD = 0

		// DoubleControl
		const val DOUBLE_GAMEPAD_1 = 0
		const val DOUBLE_GAMEPAD_2 = 1
	}

	/** Drive Ports (CAN) */
	object DriveBase {
		const val LEFT_BACK_MOTOR_PORT = 2
		const val LEFT_FRONT_MOTOR_PORT = 1
		const val RIGHT_BACK_MOTOR_PORT = 4
		const val RIGHT_FRONT_MOTOR_PORT = 3
	}

	/** Flywheel ports */
	object Flywheel {
		// PWM
		const val RIGHT_SERVO_PORT = 0
		const val LEFT_SERVO_PORT = 1

		// CAN
		const val MOTOR_PORT = 11
	}

	/** Rotator Ports */
	object Rotator {
		// CAN
		const val LEFT_MOTOR_PORT = 12
		const val RIGHT_MOTOR_PORT = 5

		// DIO
		const val FWD_LIMIT_PORT = 0
		const val BWD_LIMIT_PORT = 1
	}

	/** Miscellaneous Sensor Ports */
	object Sensors {
		// DIO
		const val RANGE_FINDER_A_PING = 7
		const val RANGE_FINDER_A_ECHO = 6
		const val RANGE_FINDER_B_PING = 9
		const val RANGE_FINDER_B_ECHO = 8
		const val BALL_DETECTOR_PORT = 3
	}
}
