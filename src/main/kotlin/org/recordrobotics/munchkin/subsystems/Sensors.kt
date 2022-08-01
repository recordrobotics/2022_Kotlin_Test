package org.recordrobotics.munchkin.subsystems

import edu.wpi.first.networktables.NetworkTableEntry
import edu.wpi.first.wpilibj.DigitalInput
import edu.wpi.first.wpilibj.Ultrasonic
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.recordrobotics.munchkin.Constants
import org.recordrobotics.munchkin.RobotMap

class Sensors : SubsystemBase() {
	private val _ballDetector = DigitalInput(RobotMap.Sensors.BALL_DETECTOR_PORT)
	private val _rangeFinderA =
		Ultrasonic(RobotMap.Sensors.RANGE_FINDER_A_PING, RobotMap.Sensors.RANGE_FINDER_A_ECHO)
	private val _rangeFinderB =
		Ultrasonic(RobotMap.Sensors.RANGE_FINDER_B_PING, RobotMap.Sensors.RANGE_FINDER_B_ECHO)

	// Dashboard entries
	private val _entryRangeA: NetworkTableEntry
	private val _entryRangeB: NetworkTableEntry
	private val _entryBallDetection: NetworkTableEntry

	init {
		_rangeFinderA.isEnabled = true
		_rangeFinderB.isEnabled = true

		// Set up dashboard
		val tab = Shuffleboard.getTab(Constants.DATA_TAB)
		_entryRangeA = tab.add("Range Finder A", 0.0).entry
		_entryRangeB = tab.add("Range Finder B", 0.0).entry
		_entryBallDetection = tab.add("Ball Detection", false).entry
	}

	/** @return average range returned in millimeters */
	val distance: Double
		get() = (_rangeFinderA.rangeMM + _rangeFinderB.rangeMM) / 2

	/** @return A's range */
	val aDistance: Double
		get() = _rangeFinderA.rangeMM

	/** @return B's range */
	val bDistance: Double
		get() = _rangeFinderB.rangeMM

	/**
	 * returns ball detector state
	 * @return ball detector switch is active (true = active, false = not active)
	 */
	val ballDetector: Boolean
		get() = !_ballDetector.get()

	/** Updates dashboard entries */
	override fun periodic() {
		_entryRangeA.setDouble(aDistance)
		_entryRangeB.setDouble(bDistance)
		_entryBallDetection.setBoolean(ballDetector)
	}
}
