package team498.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import team498.robot.commands.auto.common.AutoDrive;
import team498.robot.commands.auto.common.AutoIntake;
import team498.robot.commands.auto.common.AutoTurn;

/**
 *
 */
public class FromRightSwitchToRightScaleStrategy extends CommandGroup {

	public FromRightSwitchToRightScaleStrategy() {
		System.out.println("Going to Right Scale from Right Switch!");

		// reset arm positions
		//addSequential(new AutoLift(false));
		//addSequential(new AutoArmPosition(0));
		// reverse 1 units back
		addSequential(new AutoDrive(-.7, -16.2));
		// rotates right
		addSequential(new AutoTurn(90));
		// drives 2.5 units
		addSequential(new AutoDrive(.7, 40.5));
		// rotates left
		addSequential(new AutoTurn(-90));
		// drives forward 1 unit
		addSequential(new AutoDrive(.7, 16.2));
		// intake cube
		addSequential(new AutoIntake(.6, .6));
		// reverses back 5 units
		addSequential(new AutoDrive(-.7, -81));
		// rotates right
		addSequential(new AutoTurn(90));
		// drives forward 7.5 units
		addSequential(new AutoDrive(.7, 121.5));
		// rotates left
		addSequential(new AutoTurn(-90));
		// drive forward 1 unit
		addSequential(new AutoDrive(.7, 16.2));
		// raises tower
		//addSequential(new AutoLift(true));
		// raises arm
		//addSequential(new AutoArmPosition(165));
		// releases cube
		addSequential(new AutoIntake(-.8, -.8));

		// done for now :)
	}
}
