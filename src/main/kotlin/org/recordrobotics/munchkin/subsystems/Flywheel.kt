package org.recordrobotics.munchkin.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX
import edu.wpi.first.wpilibj.Servo
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.recordrobotics.munchkin.RobotMap

class Flywheel : SubsystemBase() {
	// motor, servo, and limit switch variables
	private val _motor = WPI_TalonFX(RobotMap.Flywheel.MOTOR_PORT)
	private val _leftServo = Servo(RobotMap.Flywheel.LEFT_SERVO_PORT)
	private val _rightServo = Servo(RobotMap.Flywheel.RIGHT_SERVO_PORT)

	/** resets motor and servos upon creation */
	init {
		spin(0.0)
	}

	/**
	 * spins motor at speed 'speed'
	 * @param speed speed to spin motor
	 */
	fun spin(speed: Double) {
		_motor[ControlMode.PercentOutput] = Subsystems.limitSpeed(speed)
	}

	/** sets the servos to shooting position (launching the ball into the flywheel in the process) */
	fun shootServos() {
		_leftServo.set(LEFT_SERVO_SHOOT)
		_rightServo.set(RIGHT_SERVO_SHOOT)
	}

	/** puts the servos back into idle position */
	fun resetServos() {
		_leftServo.set(LEFT_SERVO_RESET)
		_rightServo.set(RIGHT_SERVO_RESET)
	}

	companion object {
		// constants related to servo positions
		private const val RIGHT_SERVO_SHOOT = 0.0
		private const val LEFT_SERVO_SHOOT = 0.33
		private const val RIGHT_SERVO_RESET = 0.33
		private const val LEFT_SERVO_RESET = 0.0
	}
}
