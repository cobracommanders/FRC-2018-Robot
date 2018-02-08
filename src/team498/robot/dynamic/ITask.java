package team498.robot.dynamic;

/**
 * This is the task interface, and should never be used
 * @author Micah Neitz <br/> Team 498
 * <br/>Team 498
 */
public interface ITask {
	abstract void Init();
	abstract void Run();
	abstract void Execute();
	abstract void Change(boolean value) throws Exception;
	abstract void Change(double value) throws Exception;
	abstract boolean IsButton();
}
