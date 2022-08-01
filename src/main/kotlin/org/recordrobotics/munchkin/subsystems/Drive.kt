// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package org.recordrobotics.munchkin.subsystems

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.recordrobotics.munchkin.RobotMap.DriveBase

class Drive : SubsystemBase() {
	private val _leftMotors =
		MotorControllerGroup(
			CANSparkMax(DriveBase.LEFT_FRONT_MOTOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushless),
			CANSparkMax(DriveBase.LEFT_BACK_MOTOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushless)
		)
	private val _rightMotors =
		MotorControllerGroup(
			CANSparkMax(DriveBase.RIGHT_FRONT_MOTOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushless),
			CANSparkMax(DriveBase.RIGHT_BACK_MOTOR_PORT, CANSparkMaxLowLevel.MotorType.kBrushless)
		)
	private val _differentialDrive = DifferentialDrive(_leftMotors, _rightMotors)

	init {
		_leftMotors.set(0.0)
		_rightMotors.set(0.0)
	}

	/** Drive the robot */
	fun move(longSpeed: Double, latSpeed: Double) {
		// Arcade drive expects rotational inputs, while get translational
		// inputs. Therefore the values must be switched around
		// https://docs.wpilib.org/en/stable/docs/software/hardware-apis/motors/wpi-drive-classes.html
		_differentialDrive.arcadeDrive(
			Subsystems.limitSpeed(latSpeed), Subsystems.limitSpeed(-longSpeed)
		)
	}
}
