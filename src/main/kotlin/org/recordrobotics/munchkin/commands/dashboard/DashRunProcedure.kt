package org.recordrobotics.munchkin.commands.dashboard

import edu.wpi.first.wpilibj2.command.CommandBase
import org.recordrobotics.munchkin.util.Procedure

class DashRunProcedure(private val _procedure: Procedure) : CommandBase() {
	override fun initialize() {
		_procedure.execute()
	}

	override fun isFinished(): Boolean {
		return true
	}
}
