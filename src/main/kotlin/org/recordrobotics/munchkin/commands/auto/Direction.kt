package org.recordrobotics.munchkin.commands.auto

/** Direction for auto commands */
enum class Direction(private val _value: Int) {
	// Rotator
	BACKWARD(-1),
	FORWARD(1), // Climbers
	UP(-1),
	DOWN(1);

	fun value(): Int {
		return _value
	}
}
