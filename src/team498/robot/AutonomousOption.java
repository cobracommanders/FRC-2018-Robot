package team498.robot;

public class AutonomousOption {
	RobotStartPosition startPosition;
	AutoStrategy strategy;
	
	public AutonomousOption(RobotStartPosition startPosition) {
		this.startPosition = startPosition;
	}
	
	public AutonomousOption(RobotStartPosition startPosition, AutoStrategy strategy) {
		this.startPosition = startPosition;
		this.strategy  = strategy;
	}
	
	public RobotStartPosition getStartPosition() {
		return this.startPosition;
	}
	
	public AutoStrategy getStrategy() {
		return this.strategy;
	}
	
	public void setStartPosition(RobotStartPosition startPosition) {
		this.startPosition = startPosition;
	}
	
	public void setStrategy(AutoStrategy strategy) {
		this.strategy = strategy;
	}
}
