package org.recordrobotics.munchkin.subsystems

// put static functions that are used by multiple subsystems here
object Subsystems {
	private const val SPEED_LIMIT = 0.85

	/**
	 * Checks a motor speed value against Constants.SPEED_LIMIT
	 * @param speed speed value to check
	 * @return speed to spin motor (positive or negative)
	 */
	fun limitSpeed(speed: Double): Double {
		return if (speed > 0) {
			Math.min(speed, SPEED_LIMIT)
		} else {
			Math.max(speed, -SPEED_LIMIT)
		}
	}
}
