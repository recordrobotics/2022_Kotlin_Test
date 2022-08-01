package org.recordrobotics.munchkin.subsystems

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import edu.wpi.first.wpilibj.DigitalInput
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.recordrobotics.munchkin.RobotMap

class Acquisition : SubsystemBase() {
	// motor and limit switch variables
	private val _spinMotor =
		CANSparkMax(RobotMap.Acquisition.SPIN_MOTOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushless)
	private val _tiltMotor =
		CANSparkMax(RobotMap.Acquisition.TILT_MOTOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushed)
	private val _ballChannelMotor =
		CANSparkMax(
			RobotMap.Acquisition.BALL_CHANNEL_MOTOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushless
		)
	private val _tiltLimitSwitch = DigitalInput(RobotMap.Acquisition.LIMIT_SWITCH)

	init {
		_spinMotor.set(0.0)
		_tiltMotor.set(0.0)
		_ballChannelMotor.set(0.0)
	}

	/**
	 * returns the state of the acquisition tilt
	 * @return true if down, false if up
	 */
	val tiltState: Boolean
		get() = !_tiltLimitSwitch.get()

	/**
	 * spins spin motor at 'speed' speed and ball channel motor at '-5/3 * speed' speed
	 * @param speed base value for speed calculations, always >0
	 */
	fun spin(speed: Double) {
		_spinMotor.set(Subsystems.limitSpeed(speed))
		_ballChannelMotor.set(Subsystems.limitSpeed(speed * BALL_CHANNEL_MOD))
	}

	/**
	 * spins tilt motor at 'speed' speed if it's raising it or lowering it and hasn't hit the limit
	 * switch
	 * @param speed speed of motor
	 */
	fun tilt(speed: Double) {
		if (speed < 0 && _tiltLimitSwitch.get() || speed > 0) {
			_tiltMotor.set(Subsystems.limitSpeed(speed))
		} else {
			_tiltMotor.set(0.0)
		}
	}

	/**
	 * returns if the acquisition is spinning (this is used for dashboard)
	 * @return acquisition spinning (true = spinning, false = not spinning)
	 */
	val spinState: Boolean
		get() = _spinMotor.get() != 0.0

	companion object {
		// constant for ball channel motor speed modifier
		private const val BALL_CHANNEL_MOD = -5.0 / 3
	}
}
