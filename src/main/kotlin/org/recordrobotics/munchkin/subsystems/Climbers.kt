package org.recordrobotics.munchkin.subsystems

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import edu.wpi.first.networktables.NetworkTableEntry
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.recordrobotics.munchkin.Constants
import org.recordrobotics.munchkin.RobotMap

class Climbers : SubsystemBase() {
	private val _motorLeft =
		CANSparkMax(RobotMap.Climbers.LEFT_MOTOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushless)
	private val _motorRight =
		CANSparkMax(RobotMap.Climbers.RIGHT_MOTOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushless)
	private val _motors = MotorControllerGroup(_motorLeft, _motorRight)
	private val _encoder = _motorLeft.encoder
	private val _entryEncoder: NetworkTableEntry

	init {
		_motors.set(0.0)
		val tab = Shuffleboard.getTab(Constants.DATA_TAB)
		_entryEncoder = tab.add("Climbers position", 0.0).entry
	}

	/** Stops the motors. */
	fun stop() {
		_motors.stopMotor()
	}

	/**
	 * Gets encoder values.
	 * @return Encoder value as a double.
	 */
	val encoderValue: Double
		get() = _encoder.position

	/** Resets encoder value to zero. */
	fun resetEncoderValue() {
		_encoder.position = 0.0
	}

	/**
	 * Moves lift up and down.
	 * @param v how fast the lift motors spins.
	 */
	fun move(v: Double) {
		_motors.set(Subsystems.limitSpeed(v))
	}

	/** Update dashboard data */
	override fun periodic() {
		_entryEncoder.setDouble(encoderValue)
	}
}
