package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.auto.common.AutoArmPosition;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoLift;
import team498.robot.commands.auto.common.AutoTurn;

/**
 *
 */
public class FromLeftSwitchToRightScaleStrategy extends CommandGroup {

	public FromLeftSwitchToRightScaleStrategy() {
		System.out.println("Going to Right Scale from Left Switch!");

		// resets arm
		addSequential(new AutoLift(false));
		addSequential(new AutoArmPosition(0));

		// reverses 1 unit
		addSequential(new AutoDrive(-.7, -16.2));
		// rotates left
		addSequential(new AutoTurn(-90));
		// drives forward 2.5 units
		addSequential(new AutoDrive(.7, 40.5));
		// rotates right
		addSequential(new AutoTurn(90));
		// drives forward 1 unit
		addSequential(new AutoDrive(.7, 16.2));
		// intakes cube
		addSequential(new AutoIntake(.6, .6));
		// rotates left
		addSequential(new AutoTurn(-90));
		// drives forward 2 units
		addSequential(new AutoDrive(.7, 32.4));
		// rotates right
		addSequential(new AutoTurn(90));
		// drives forward 12 units
		addSequential(new AutoDrive(.7, 194.4));
		// rotates left
		addSequential(new AutoTurn(-90));
		// drives forward 5 units
		addSequential(new AutoDrive(.7, 81));
		// rotates left
		addSequential(new AutoTurn(-90));
		// raises tower
		addSequential(new AutoLift(true));
		// raises arm
		addSequential(new AutoArmPosition(165));
		// releases cube
		addSequential(new AutoIntake(-.8, -.8));

		// done for now :)

	}
}