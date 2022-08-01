// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package org.recordrobotics.munchkin.commands.manual

import edu.wpi.first.wpilibj2.command.CommandBase
import org.recordrobotics.munchkin.control.IControlInput
import org.recordrobotics.munchkin.subsystems.Drive

/** Drive teleop command */
class ManualDrive(drive: Drive?, controls: IControlInput?) : CommandBase() {
	private val _drive: Drive
	private val _controls: IControlInput

	init {
		requireNotNull(drive) { "Drive is null" }
		requireNotNull(controls) { "Controls is null" }
		_drive = drive
		_controls = controls
		addRequirements(drive)
	}

	override fun execute() {
		_drive.move(_controls.driveLong * SPEED_MODIFIER, _controls.driveLat * SPEED_MODIFIER)
	}

	override fun end(interrupted: Boolean) {
		_drive.move(0.0, 0.0)
	}

	companion object {
		private const val SPEED_MODIFIER = 0.5
	}
}
