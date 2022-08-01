// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package org.recordrobotics.munchkin

import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj2.command.CommandScheduler

class Robot : TimedRobot() {
	private var _robotContainer: RobotContainer? = null

	/** Robot initialization */
	override fun robotInit() {
		// Create container
		_robotContainer = RobotContainer()
	}

	/** Runs every robot tick */
	override fun robotPeriodic() {
		// Run command scheduler
		CommandScheduler.getInstance().run()
	}

	/** Runs when robot enters disabled mode */
	override fun disabledInit() {
		_robotContainer!!.resetCommands()
	}

	/** Runs every tick during disabled mode */
	override fun disabledPeriodic() {
		// TODO
	}

	/** Runs when robot enters auto mode */
	override fun autonomousInit() {
		_robotContainer!!.resetCommands()
		_robotContainer!!.autoInit()
	}

	/** Runs every tick during auto mode */
	override fun autonomousPeriodic() {
		// TODO
	}

	/** Runs when robot enters teleop mode */
	override fun teleopInit() {
		_robotContainer!!.resetCommands()
		_robotContainer!!.teleopInit()
	}

	/** Runs every tick in teleop mode */
	override fun teleopPeriodic() {
		// TODO
	}
}
