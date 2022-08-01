package org.recordrobotics.munchkin.subsystems

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import edu.wpi.first.networktables.NetworkTableEntry
import edu.wpi.first.wpilibj.DigitalInput
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.recordrobotics.munchkin.Constants
import org.recordrobotics.munchkin.RobotMap

class Rotator : SubsystemBase() {
	private val _leftMotor =
		CANSparkMax(RobotMap.Rotator.LEFT_MOTOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushless)
	private val _rightMotor =
		CANSparkMax(RobotMap.Rotator.RIGHT_MOTOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushless)
	private val _leftEncoder = _leftMotor.encoder
	private val _rightEncoder = _rightMotor.encoder
	private val _forwardLimit = DigitalInput(RobotMap.Rotator.FWD_LIMIT_PORT)
	private val _backwardLimit = DigitalInput(RobotMap.Rotator.BWD_LIMIT_PORT)
	private val _entryEncoder: NetworkTableEntry

	init {
		_leftMotor.set(0.0)
		_rightMotor.set(0.0)
		val tab = Shuffleboard.getTab(Constants.DATA_TAB)
		_entryEncoder = tab.add("Rotator position", 0.0).entry
	} // the motors spin in opposite directions, so one encoder is always positive and the other is
	// negative

	/**
	 * gets position from encoders
	 * @return position
	 */
	val position: Double
		get() = // the motors spin in opposite directions, so one encoder is always positive and the
			// other is negative
			(_leftEncoder.position - _rightEncoder.position) / 2

	/** sets encoder values to 0 */
	fun resetEncoders() {
		_leftEncoder.position = 0.0
		_rightEncoder.position = 0.0
	}

	/**
	 * rotate lift forwards and backwards
	 * @param speed speed
	 */
	fun rotate(speed: Double) {
		if (speed > 0 && _forwardLimit.get() || speed < 0 && _backwardLimit.get()) {
			_leftMotor.set(Subsystems.limitSpeed(speed))
			_rightMotor.set(Subsystems.limitSpeed(-speed))
		} else {
			_leftMotor.set(0.0)
			_rightMotor.set(0.0)
		}
	}

	/**
	 * Return the inversed state of the forward limit switch
	 * @return true is pressed, false is not
	 */
	val isFwdLimitPressed: Boolean
		get() = !_forwardLimit.get()

/*
* Update dashboard value
*/
	override fun periodic() {
		_entryEncoder.setDouble(position)
	}
}
