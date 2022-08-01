package org.recordrobotics.munchkin.commands.dashboard

import edu.wpi.first.wpilibj2.command.CommandBase

class DashRunFunction(private val func: () -> Unit) : CommandBase() {

	override fun initialize() = func()

	override fun isFinished(): Boolean {
		return true
	}
}
