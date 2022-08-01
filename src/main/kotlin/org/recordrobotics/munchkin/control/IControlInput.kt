package org.recordrobotics.munchkin.control

/** Specifies all control inputs needed for the robot */
interface IControlInput {

	/** Flywheel has three states: OFF - disable LOW - low target shot HIGH - high target shot */
	enum class FlywheelState {
		OFF,
		LOW,
		HIGH
	}

	/**
	 * Logitudinal drive input (forward & backward) value
	 *
	 * [-1, 0) - backward; (0, 1] - forward
	 */
	val driveLong: Double

	/**
	 * Latitudinal drive input (left & right) value
	 *
	 * [-1, 0) - left; (0, 1] - right
	 */
	val driveLat: Double

	/**
	 * Lift rotator input value
	 *
	 * [-1, 0) - backward; (0, 1] - forward
	 */
	val rotate: Double

	/**
	 * Climber input value
	 *
	 * [-1, 0) - down; (0, 1] - up
	 */
	val climb: Double

	/**
	 * Acquisition spin input value
	 *
	 * [-1, 0) - backward; (0, 1] - forward
	 */
	val acqSpin: Double

	/**
	 * Acquisition tilt input value
	 *
	 * [-1, 0) - in; (0, 1] - out
	 */
	val acqTilt: Double

	/**
	 * Flywheel requested state
	 *
	 * OFF - off, LOW - low speed, HIGH - high speed
	 */
	val flywheel: FlywheelState

	/**
	 * Servo input
	 *
	 * false - servos down; true - servos up
	 */
	val servos: Boolean
}
