package org.recordrobotics.munchkin

import edu.wpi.first.networktables.NetworkTableEntry
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.CommandScheduler
import edu.wpi.first.wpilibj2.command.Subsystem
import org.recordrobotics.munchkin.commands.dashboard.DashResetClimbEncoder
import org.recordrobotics.munchkin.commands.dashboard.DashRunProcedure
import org.recordrobotics.munchkin.commands.group.SeqLiftMid
import org.recordrobotics.munchkin.commands.manual.*
import org.recordrobotics.munchkin.control.DoubleControl
import org.recordrobotics.munchkin.control.IControlInput
import org.recordrobotics.munchkin.control.LegacyControl
import org.recordrobotics.munchkin.subsystems.*
import org.recordrobotics.munchkin.util.Pair

/** Contains subsystems, control and command scheduling */
class RobotContainer {
	// Control Scheme
	private var _controlInput: IControlInput

	// Subsystems
	private val _acquisition: Acquisition
	private val _climbers: Climbers
	private val _drive: Drive
	private val _flywheel: Flywheel
	private val _rotator: Rotator

	// This will be used in auto commands later
	private val _sensors: Sensors

	// Commands
	private var _teleopPairs: MutableList<Pair<Subsystem, Command>>? = null
	private var _autoCommand: Command? = null

	// Dashboard data
	private var _entryControl: NetworkTableEntry? = null

	init {
		_controlInput = LegacyControl(RobotMap.Control.LEGACY_GAMEPAD)
		_acquisition = Acquisition()
		_climbers = Climbers()
		_flywheel = Flywheel()
		_rotator = Rotator()
		_drive = Drive()
		_sensors = Sensors()
		initEntries()
		initDashCommands()
		initTeleopCommands()
		initAutoCommand()
	}

	private fun initTeleopCommands() {
		_teleopPairs =
			mutableListOf(
				Pair(_acquisition, ManualAcquisition(_acquisition, _controlInput)),
				Pair(_climbers, ManualClimbers(_climbers, _controlInput)),
				Pair(_flywheel, ManualFlywheel(_flywheel, _controlInput)),
				Pair(_rotator, ManualRotator(_rotator, _controlInput)),
				Pair(_drive, ManualDrive(_drive, _controlInput))
			)
		_entryControl!!.setValue(_controlInput.toString())
	}

	private fun initAutoCommand() {
		_autoCommand = SeqLiftMid(_rotator, _climbers)
	}

	/** Create dashboard commands */
	private fun initDashCommands() {
		val tab = Shuffleboard.getTab(Constants.COMMANDS_TAB)
		tab.add("Reset Climbers Encoder", DashResetClimbEncoder(_climbers))
		tab.add("Legacy Control", DashRunProcedure { controlLegacy() })
		tab.add("Double Control", DashRunProcedure { controlDouble() })
	}

	/** Initialize dashboard entries */
	private fun initEntries() {
		val tab = Shuffleboard.getTab(Constants.DATA_TAB)
		_entryControl = tab.add("Control Type", "").entry
	}

	/** Set control scheme to legacy */
	private fun controlLegacy() {
		resetCommands()
		_controlInput = LegacyControl(RobotMap.Control.LEGACY_GAMEPAD)
		initTeleopCommands()
		teleopInit()
	}

	/** Set control scheme to double */
	private fun controlDouble() {
		resetCommands()
		_controlInput =
			DoubleControl(RobotMap.Control.DOUBLE_GAMEPAD_1, RobotMap.Control.DOUBLE_GAMEPAD_2)
		initTeleopCommands()
		teleopInit()
	}

	/** Create teleop commands */
	fun teleopInit() {
		for (c in _teleopPairs!!) {
			c.key.defaultCommand = c.value
		}
	}

	/** Create autonomous mode commands */
	fun autoInit() {
		CommandScheduler.getInstance().schedule(true, _autoCommand)
	}

	/** Clear commands */
	fun resetCommands() {
		CommandScheduler.getInstance().cancelAll()
	}
}
