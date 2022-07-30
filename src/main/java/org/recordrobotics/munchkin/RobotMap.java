package org.recordrobotics.munchkin;

/**
 * Hardware ports for computer and robot
 */
public class RobotMap {

	/**
	 * Acquisition ports
	 */
	public class Acquisition {
		// DIO
		public static final int LIMIT_SWITCH = 2;

		// CAN
		public static final int SPIN_MOTOR_PORT = 7;
		public static final int BALL_CHANNEL_MOTOR_PORT = 8;
		public static final int TILT_MOTOR_PORT = 9;
	}

	/**
	 * Climber ports (CAN)
	 */
	public class Climbers {
		public static final int LEFT_MOTOR_PORT = 10;
		public static final int RIGHT_MOTOR_PORT = 6;
	}

	/**
	 * Control ports (PC USB)
	 */
	public class Control {
		// LegacyControl
		public static final int LEGACY_GAMEPAD = 0;

		// DoubleControl
		public static final int DOUBLE_GAMEPAD_1 = 0;
		public static final int DOUBLE_GAMEPAD_2 = 1;
	}

	/**
	 * Drive Ports (CAN)
	 */
	public class DriveBase {
		public static final int LEFT_BACK_MOTOR_PORT = 2;
		public static final int LEFT_FRONT_MOTOR_PORT = 1;
		public static final int RIGHT_BACK_MOTOR_PORT = 4;
		public static final int RIGHT_FRONT_MOTOR_PORT = 3;
	}

	/**
	 * Flywheel ports
	 */
	public class Flywheel {
		// PWM
		public static final int RIGHT_SERVO_PORT = 0;
		public static final int LEFT_SERVO_PORT = 1;

		// CAN
		public static final int MOTOR_PORT = 11;
	}

	/**
	 * Rotator Ports
	 */
	public class Rotator {
		// CAN
		public static final int LEFT_MOTOR_PORT = 12;
		public static final int RIGHT_MOTOR_PORT = 5;

		// DIO
		public static final int FWD_LIMIT_PORT = 0;
		public static final int BWD_LIMIT_PORT = 1;

	}

	/**
	 * Miscellaneous Sensor Ports
	 */
	public class Sensors {
		// DIO
		public static final int RANGE_FINDER_A_PING = 7;
		public static final int RANGE_FINDER_A_ECHO = 6;
		public static final int RANGE_FINDER_B_PING = 9;
		public static final int RANGE_FINDER_B_ECHO = 8;
		public static final int BALL_DETECTOR_PORT = 3;
	}
}
